package com.thechance.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = Purple700,
    secondary = LightTernaryBrandColor,
    background = DarkBackgroundColor,
    onBackground = DarkOnBackgroundColor,
    surface = DarkCardColor,
    onSurface = DarkOnSurfaceColor,
)

private val LightColorPalette = lightColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = Purple700,
    secondary = LightSecondaryBrandColor,
    background = LightBackgroundColor,
    surface = LightCardColor,


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
fun ClubsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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