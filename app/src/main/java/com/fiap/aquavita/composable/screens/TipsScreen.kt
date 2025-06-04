package com.fiap.aquavita.composable.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fiap.aquavita.composable.util.AquaBottomBar
import com.fiap.aquavita.composable.util.TipCard
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Background
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.utils.Tip
import com.fiap.aquavita.viewmodels.HomeViewModel

/**
 * Tela de dicas sobre preservação de água que exibe uma lista de recomendações para economia
 * e uso consciente de recursos hídricos.
 *
 * @param nav O controlador de navegação usado para navegar entre telas
 * @param vm O ViewModel que gerencia o estado da tela, obtendo dados e controlando o estado de carregamento
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(nav: NavController, vm: HomeViewModel = viewModel()) {
    val ui = vm.uiState

    val tips = listOf(
        Tip(
            title = "Reaproveitamento de Água",
            body = "A água utilizada para lavar roupas pode ser reaproveitada para limpar calçadas e pisos, economizando até 45 % de consumo mensal."
        ),
        Tip(
            title = "Banhos Conscientes",
            body = "Reduzir o tempo de banho em 5 minutos gera economia de até 50 L por pessoa. Feche a torneira ao se ensaboar."
        ),
        Tip(
            title = "Captação de Água da Chuva",
            body = "Instalar um sistema de captação de água pluvial para regar plantas pode economizar 1 000 L mensais, além de diminuir a conta."
        ),
        Tip(
            title = "Mantenha a torneira fechada ao ensaboar a louça",
            body = "a economia é de 97 litros (casa) e 223 litros (apartamento). Faça o mesmo quando desfolhar verduras e hortaliças, descascar frutas e legumes, cortar aves, carnes, peixes etc."
        ),
        Tip(
            title = "Ao lavar o carro, use um balde",
            body = "em vez de mangueira, a economia é de 176 litros."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("AquaVita", color = AquaBlue, fontWeight = FontWeight.Bold)
            })
        },
        bottomBar = { AquaBottomBar(nav) }
    ) { inner ->

        when {
            ui.loading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(inner)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(Modifier.height(4.dp))

                    Text(
                        "Educação Hídrica",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = AquaBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Aprenda como preservar nosso recurso mais precioso com estas dicas importantes.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextDefault
                    )
                    Spacer(Modifier.height(8.dp))
                }

                items(tips) { tip ->
                    TipCard(tip)
                }

                item {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "Teste seus conhecimentos sobre preservação da água.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextDefault
                    )
                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = { nav.navigate("quiz") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text("Iniciar Quiz")
                    }
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}