package com.fiap.aquavita.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiap.aquavita.models.QuizQuestion
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
class QuizViewModel @Inject constructor() : ViewModel() {

    val questions = listOf(
        QuizQuestion(
            "Fechar a torneira enquanto escova os dentes pode economizar até 12 litros de água por escovação.",
            correct = true,
            explanation = "São 12 L poupados em média por escovação com torneira fechada."
        ),
        QuizQuestion(
            "Banhos de 15 minutos são considerados econômicos para o consumo de água.",
            correct = false,
            explanation = "O ideal é ≤ 5 min. Em 15 min chegam a 135 L de água gastos."
        ),
        QuizQuestion(
            "Reutilizar a água da máquina de lavar para limpar calçadas é uma prática sustentável.",
            correct = true,
            explanation = "Água de enxágue contém pouco sabão e serve para limpeza pesada."
        ),
        QuizQuestion(
            "Vazamentos imperceptíveis em torneiras não geram impacto significativo na conta.",
            correct = false,
            explanation = "Um pinga-pinga pode desperdiçar > 40 L por dia."
        ),
        QuizQuestion(
            "Instalar arejadores nas torneiras reduz o consumo sem perder pressão.",
            correct = true,
            explanation = "Arejadores misturam ar à água, economizando até 50 %."
        )
    )

    var answers by mutableStateOf(MutableList<Boolean?>(questions.size) { null })
        private set

    fun selectAnswer(index: Int, answer: Boolean) {
        answers = answers.toMutableList().also { it[index] = answer }
    }

    val isQuizFinished get() = answers.none { it == null }
}