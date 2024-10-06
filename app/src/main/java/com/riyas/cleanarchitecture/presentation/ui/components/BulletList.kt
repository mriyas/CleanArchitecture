package com.riyas.cleanarchitecture.presentation.ui.components

import AppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            style = AppTheme.typography.h2,
            color = AppTheme.colors.primaryTextColor,
        )
        Spacer(modifier = Modifier.height(5.dp))
        items.forEach {
            Row {
                Text(
                    modifier = Modifier.padding(horizontal = indent),
                    text = "\u2022",
                    style = AppTheme.typography.h3,
                    color = AppTheme.colors.secondaryTextColor,
                )
                Text(
                    text = it,
                    modifier = Modifier.weight(1f, fill = true),
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.secondaryTextColor,
                )
            }
            if (lineSpacing > 0.dp && it != items.last()) {
                Spacer(modifier = Modifier.height(lineSpacing))
            }
        }
    }
}

