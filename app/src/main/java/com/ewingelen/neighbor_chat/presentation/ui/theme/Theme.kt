package com.ewingelen.neighbor_chat.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = White,
    secondary = Red,
    secondaryVariant = White,
    background = Blue,
    onBackground = White,
    surface = Cyan,
    onSurface = Black
)

private val LightColorPalette = lightColors(
    primary = White,
    secondary = Red,
    secondaryVariant = White,
    background = Blue,
    onBackground = White,
    surface = Cyan,
    onSurface = Black
)

@Composable
fun NeighborChatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}