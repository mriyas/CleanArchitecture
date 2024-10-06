package com.riyas.cleanarchitecture.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    isDialogIndicator: Boolean = true,
) {
    if (isDialogIndicator) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            ProgressBar(modifier = modifier)
        }
    } else {
        ProgressBar(modifier = modifier)
    }
}


@Composable
private fun ProgressBar(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}