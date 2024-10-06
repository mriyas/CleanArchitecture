package com.riyas.cleanarchitecture.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val background: Color,
    val surface: Color,
    val error: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val information: Color,
)

internal val lightColorSchema = Colors(
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFFF1741),
    secondaryTextColor = Color(0xFF212121),
    primaryTextColor = Color(0xFF212121),
    information = Color(0xFF4CAF50),
)

internal val darkColorSchema = Colors(
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    error = Color(0xFFFC4566),
    secondaryTextColor = Color(0xFFCBCBCB),
    primaryTextColor = Color(0xFFFFFFFF),
    information = Color(0xFF4CAF50),
)
