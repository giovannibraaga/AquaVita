package com.fiap.aquavita.composable.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fiap.aquavita.R
import com.fiap.aquavita.composable.util.AquaBottomBar
import com.fiap.aquavita.composable.util.CityHelpSheet
import com.fiap.aquavita.composable.util.rememberMapLibreViewWithLifecycle
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.viewmodels.City
import com.fiap.aquavita.viewmodels.MapViewModel
import com.google.gson.JsonPrimitive
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.plugins.annotation.SymbolManager
import org.maplibre.android.plugins.annotation.SymbolOptions

/**
 * Tela do mapa que exibe pontos de distribuição de água em diferentes cidades.
 *
 * Esta tela utiliza o MapLibre para renderizar um mapa interativo que mostra marcadores
 * em locais onde há pontos de distribuição de água. Os usuários podem tocar nos marcadores
 * para ver mais informações sobre os pontos de distribuição em cada cidade.
 *
 * @param nav O controlador de navegação usado para transitar entre telas
 * @param vm O ViewModel que gerencia os dados do mapa, incluindo a lista de cidades e pontos de ajuda
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    nav: NavController,
    vm: MapViewModel = viewModel()
) {
    var selected by remember { mutableStateOf<City?>(null) }
    val mapView   = rememberMapLibreViewWithLifecycle()
    var symbolMgr by remember { mutableStateOf<SymbolManager?>(null) }

    val context  = LocalContext.current
    val iconName = "help-icon"

    Scaffold(
        topBar    = { TopAppBar(title = { Text("AquaVita", color = AquaBlue) }) },
        bottomBar = { AquaBottomBar(nav) }
    ) { inner ->

        Box(Modifier.fillMaxSize().padding(inner)) {

            AndroidView(factory = { mapView }) { mv ->
                mv.getMapAsync { map ->
                    map.setStyle("https://demotiles.maplibre.org/style.json") { style ->
                        if (style.getImage(iconName) == null) {
                            style.addImage(
                                iconName,
                                BitmapFactory.decodeResource(context.resources, R.drawable.gps_icon),
                                false
                            )
                        }
                        symbolMgr = SymbolManager(mv, map, style).apply {
                            iconAllowOverlap = true
                            iconIgnorePlacement = true
                        }
                        vm.cities.firstOrNull()?.let { c ->
                            map.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(c.lat, c.lon), 4.5
                                )
                            )
                        }
                        symbolMgr?.addClickListener { sym ->
                            sym.data?.asString?.let { cityId ->
                                selected = vm.cities.firstOrNull { it.id == cityId }
                            }
                            true
                        }
                    }
                }
            }

            LaunchedEffect(vm.cities, symbolMgr) {
                val sm = symbolMgr ?: return@LaunchedEffect
                sm.deleteAll()
                vm.cities.forEach { city ->
                    sm.create(
                        SymbolOptions()
                            .withLatLng(LatLng(city.lat, city.lon))
                            .withIconImage(iconName)
                            .withIconSize(0.07f)
                            .withData(JsonPrimitive(city.id))
                    )
                }
            }
            Row(
                Modifier.align(Alignment.TopStart).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painterResource(R.drawable.gps_icon),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(4.dp))
                Text("Pontos de distribuição de água",
                    style = MaterialTheme.typography.labelSmall)
            }
            if (selected != null) {
                CityHelpSheet(
                    city    = selected!!,
                    vm      = vm,
                    onClose = { selected = null }
                )
            }
        }
    }
}