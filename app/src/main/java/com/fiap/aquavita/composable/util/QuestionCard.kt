package com.fiap.aquavita.composable.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.utils.QuizQuestion

@Composable
fun QuestionCard(
    question: QuizQuestion,
    index: Int,
    total: Int,
    selected: Boolean?,
    onSelect: (Boolean) -> Unit
) {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {

            Text(
                "Questão ${index + 1}/$total",
                style = MaterialTheme.typography.labelSmall,
                color = AquaBlue
            )
            Spacer(Modifier.height(4.dp))

            Text(
                question.statement,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                color = TextDefault
            )
            Spacer(Modifier.height(12.dp))

            if (selected == null) {
                OptionRow("Verdadeiro") { onSelect(true) }
                Spacer(Modifier.height(8.dp))
                OptionRow("Falso")      { onSelect(false) }
            } else {
                val isCorrect = selected == question.correct
                val bg = if (isCorrect) Color(0xFFCCF4E5) else AquaBlue.copy(alpha = 0.2f)
                val txt = if (isCorrect) Color(0xFF065F46) else Color(0xFF003D52)

                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(bg, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = if (isCorrect) "Resposta: Correta" else "Resposta: Falso",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = txt
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(question.explanation, style = MaterialTheme.typography.bodySmall, color = TextDefault)
            }
        }
    }
}
