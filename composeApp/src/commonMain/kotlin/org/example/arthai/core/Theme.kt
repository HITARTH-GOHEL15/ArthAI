package org.example.arthai.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    onPrimary = TextWhite,

    secondary = GreenSecondary,
    onSecondary = TextWhite,

    background = NightBlack,
    onBackground = TextWhite,

    surface = NightSurface,
    onSurface = TextWhite,

    error = ErrorRed,
    onError = TextWhite,

    outline = GreenBorder
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = TextWhite,

    secondary = GreenSecondary,
    onSecondary = TextWhite,

    background = NightBlack,
    onBackground = TextWhite,

    surface = NightSurface,
    onSurface = TextWhite,

    error = ErrorRed,
    onError = TextWhite,

    outline = GreenBorder
)

@Composable
fun HITO_3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
