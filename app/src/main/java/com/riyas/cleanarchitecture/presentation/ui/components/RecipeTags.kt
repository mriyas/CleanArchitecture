package com.riyas.cleanarchitecture.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
internal fun RecipeTags(recipeTags: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically)
    ) {
        recipeTags.forEach { tag ->
            key(tag) {
                Chip(
                    modifier = Modifier,
                    shape = RoundedCornerShape(30),
                    enabled = false,
                    onClick = {},
                    colors = ChipDefaults.chipColors(
                        disabledBackgroundColor = Color(
                            Random.nextInt(256),
                            Random.nextInt(256),
                            Random.nextInt(256)
                        ),
                        disabledContentColor = Color.White
                    ),
                ) {
                    Text(
                        text = tag,
                        modifier = Modifier.weight(1f, fill = false),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}