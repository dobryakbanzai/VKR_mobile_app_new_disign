package com.example.vkr_new_disign.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkAccent,
    primaryVariant = DarkText,
    secondary = Teal400,
    secondaryVariant = DarkAccent2,
    background = DarkBack,


)

private val LightColorPalette = lightColors(
    primary = LightAccent,
    primaryVariant = LightText,
    secondary = Teal200,
    secondaryVariant = LightAccent2,
    background = LightBack,




    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun VKR_new_disignTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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

