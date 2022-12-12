package com.thechance.identity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = LightCardBackground,
    secondary = LightSecondaryBrandColor,
    background = LightCardBackground,
//    surface = Color.Black,
//    onPrimary = Color.Black,
//    onSurface = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = LightCardBackground,
    secondary = LightSecondaryBrandColor,
    background = LightCardBackground,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,

)

@Composable
fun IdentityTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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