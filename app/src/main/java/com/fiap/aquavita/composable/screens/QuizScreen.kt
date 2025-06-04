package com.fiap.aquavita.composable.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fiap.aquavita.composable.util.AquaBottomBar
import com.fiap.aquavita.composable.util.QuestionCard
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Background
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.viewmodels.QuizViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /**
         * Exibe a tela do Quiz.
         *
         * @param nav Controlador de navegação para gerenciar a navegação entre telas.
         * @param vm Instância do QuizViewModel para controlar a lógica do quiz.
         */
fun QuizScreen(nav: NavController, vm: QuizViewModel = viewModel()) {

    val answers = vm.answers
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("AquaVita", color = AquaBlue, fontWeight = FontWeight.Bold)
            })
        },
        bottomBar = { AquaBottomBar(nav) }
    ) { inner ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(inner)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Text(
                    "Quiz da Água",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = AquaBlue, fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Teste seus conhecimentos sobre preservação da água",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextDefault
                )
                Spacer(Modifier.height(8.dp))
            }

            itemsIndexed(vm.questions) { idx, q ->
                QuestionCard(
                    question = q,
                    index = idx,
                    total = vm.questions.size,
                    selected = answers[idx],
                    onSelect = { vm.selectAnswer(idx, it) }
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        val correct = vm.questions.indices.count { i ->
                            vm.answers[i] == vm.questions[i].correct
                        }
                        Toast
                            .makeText(context,
                                "Você acertou $correct de ${vm.questions.size}!",
                                Toast.LENGTH_LONG)
                            .show()
                        nav.popBackStack()
                    },
                    enabled = vm.isQuizFinished,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Finalizar Quiz")
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}