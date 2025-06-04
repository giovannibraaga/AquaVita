package com.fiap.aquavita.composable.util

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.viewmodels.City
import com.fiap.aquavita.viewmodels.MapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityHelpSheet(
    city: City,
    vm: MapViewModel,
    onClose: () -> Unit
) {
    val ctx = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onClose,
        sheetState = sheetState
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(city.name, style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))

            vm.pointsFor(city.id).forEach { p ->
                ListItem(
                    headlineContent   = { Text(p.name) },
                    supportingContent = { Text("${p.capacityLiters} L disponíveis") },
                    leadingContent    = {
                        Icon(Icons.Default.Place, contentDescription = null, tint = AquaBlue)
                    },
                    trailingContent   = {
                        TextButton(onClick = {
                            val uri = "google.navigation:q=${p.lat},${p.lon}".toUri()
                            ctx.startActivity(Intent(Intent.ACTION_VIEW, uri)
                                .setPackage("com.google.android.apps.maps"))
                        }) { Text("Marcar no mapa") }
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = onClose, modifier = Modifier.fillMaxWidth()) {
                Text("Fechar")
            }
        }
    }
}