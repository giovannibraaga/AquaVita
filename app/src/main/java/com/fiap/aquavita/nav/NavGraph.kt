package com.fiap.aquavita.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.aquavita.composable.screens.HomeScreen
import com.fiap.aquavita.composable.screens.LoginScreen
import com.fiap.aquavita.composable.screens.MapScreen
import com.fiap.aquavita.composable.screens.QuizScreen
import com.fiap.aquavita.composable.screens.SignUpScreen
import com.fiap.aquavita.composable.screens.TipsScreen

/**
 * Classe de navegação principal que define o gráfico de navegação do aplicativo AquaVita.
 *
 * Esta classe é responsável por configurar e gerenciar todas as rotas de navegação entre
 * as diferentes telas do aplicativo. Ela define o fluxo de navegação começando pela tela
 * de login como destino inicial e mapeando para as diversas telas funcionais do aplicativo.
 */
class NavGraph {
    /**
     * Função Composable que configura o sistema de navegação do aplicativo AquaVita.
     *
     * - Inicializa um NavController para gerenciar a navegação entre telas
     * - Configura um NavHost com a tela de login como ponto de entrada do aplicativo
     * - Define as rotas para todas as telas principais do aplicativo
     */
    @Composable
    fun AquaVitaNav() {
        val nav = rememberNavController()
        NavHost(nav, startDestination = "login") {
            composable("home") { HomeScreen(nav) }
            composable("dicas") { TipsScreen(nav) }
            composable("login") { LoginScreen(nav) }
            composable("quiz") { QuizScreen(nav) }
            composable("map") { MapScreen(nav) }
            composable("signup") { SignUpScreen(nav) }
        }
    }
}