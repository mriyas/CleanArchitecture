package com.riyas.cleanarchitecture.presentation.ui.components

import AppTheme
import MultipleThemePreview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RecipeTags(recipeTags: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically)
    ) {
        recipeTags.forEach { tag ->
            key(tag) {
                AssistChip(
                    modifier = Modifier,
                    enabled = true,
                    onClick = {},
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color(
                            Random.nextInt(255),
                            Random.nextInt(255),
                            Random.nextInt(255),
                        ),
                    ),
                    label = {
                        Text(
                            text = tag,
                            modifier = Modifier.weight(1f, fill = false),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = AppTheme.colors.secondaryTextColor
                        )
                    }
                )
            }
        }
    }
}

@Composable
@MultipleThemePreview
private fun RecipeTags_Preview() {
    val tags: List<String> = arrayListOf("tag1", "tag2")
    AppTheme(isDarkTheme = isSystemInDarkTheme()) {
        RecipeTags(recipeTags = tags)
    }
}