package com.sanazi.eyerest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TimePickerScheme = darkColorScheme(
    primary = Yellow,
    onPrimary = DarkGray,
    primaryContainer = Yellow,
    onPrimaryContainer = Color.Black,
    surfaceContainerHighest = Color.Black,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Yellow,
    onPrimary = DarkGray,
    primaryContainer = LightGray,
    secondary = Color.Black,
    onSecondary = Color.White,
    secondaryContainer = DarkYellow,
    onSecondaryContainer = DarkGreen,
    outline = Yellow,
    tertiary = Gray,
    onTertiary = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun EyeFitTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun TimePickerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = TimePickerScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TimePickerTypography,
        content = content
    )
}