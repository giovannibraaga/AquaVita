package com.fiap.aquavita.composable.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fiap.aquavita.composable.util.AquaBottomBar
import com.fiap.aquavita.composable.util.NewsCard
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Background
import com.fiap.aquavita.viewmodels.HomeViewModel

/**
 * Tela inicial do aplicativo AquaVita que exibe notícias relacionadas à seca e água.
 *
 * Esta tela apresenta uma lista de artigos de notícias sobre água obtidos através do ViewModel.
 * Durante o carregamento dos dados, um indicador de progresso é exibido. Em caso de erro,
 * uma mensagem é mostrada ao usuário. Cada artigo é exibido como um cartão clicável que,
 * quando pressionado, abre o URL da notícia no navegador do dispositivo.
 *
 * @param navController O controlador de navegação usado para navegar entre telas
 * @param vm O ViewModel que gerencia o estado da tela, incluindo a lista de artigos e estados de carregamento/erro
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, vm: HomeViewModel = viewModel()) {

    val ui = vm.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("AquaVita", color = AquaBlue, fontWeight = FontWeight.Bold)
            })
        },
        bottomBar = { AquaBottomBar(navController) }
    ) { innerPadding ->

        when {
            ui.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            ui.error != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(ui.error, color = Color(0xFFD32F2F))
            }
            else -> LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                item {
                    Text(
                        "Notícias sobre Água",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = AquaBlue, fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                }
                items(ui.articles, key = { it.url ?: it.title.hashCode() }) { art ->
                    NewsCard(art) { url ->
                        url?.let {
                            val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}