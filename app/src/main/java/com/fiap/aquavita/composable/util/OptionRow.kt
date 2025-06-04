package com.fiap.aquavita.composable.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Placeholder
import com.fiap.aquavita.ui.theme.TextDefault


@Composable
internal fun OptionRow(text: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = false, onClick = onClick, colors = RadioButtonDefaults.colors(
            unselectedColor = Placeholder, selectedColor = AquaBlue
        ))
        Spacer(Modifier.width(6.dp))
        Text(text, color = TextDefault)
    }
}