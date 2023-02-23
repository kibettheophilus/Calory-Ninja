package com.theophiluskibet.caloryninja.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun CaloryDetailText(text: String) {
    Row(modifier = Modifier.padding(start = 5.dp)) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")
        Text(
            text = text,
            modifier = Modifier.padding(start = 10.dp),
            fontStyle = FontStyle.Italic
        )
    }
}
