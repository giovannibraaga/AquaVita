package com.fiap.aquavita.composable.util

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.utils.Tip

@Composable
fun TipCard(tip: Tip) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                tip.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = AquaBlue,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                tip.body,
                style = MaterialTheme.typography.bodySmall,
                color = TextDefault
            )
        }
    }
}