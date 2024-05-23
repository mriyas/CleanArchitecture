package com.riyas.cleanarchitecture.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun BulletList(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier,
    indent: Dp = 20.dp,
    lineSpacing: Dp = 0.dp,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(2.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(5.dp))
        items.forEach {
            Row {
                Text(
                    modifier = Modifier.padding(horizontal = indent),
                    text = "\u2022",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                )
                Text(
                    text = it,
                    modifier = Modifier.weight(1f, fill = true),
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                )
            }
            if (lineSpacing > 0.dp && it != items.last()) {
                Spacer(modifier = Modifier.height(lineSpacing))
            }
        }
    }
}

