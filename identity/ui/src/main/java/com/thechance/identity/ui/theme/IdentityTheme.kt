package com.thechance.identity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = DarkPrimaryWhiteColor,
    secondaryVariant = DarkTernaryWhiteColor,
    secondary = LightSecondaryBrandColor,
    background = DarkBackground,
    surface = DarkCardColor,
    onSecondary = DarkTernaryWhiteColor,
    onSurface = DarkSecondaryWhiteColor,
)

private val LightColorPalette = lightColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = LightPrimaryBlackColor,
    secondaryVariant = LightTernaryBlackColor,
    secondary = LightSecondaryBrandColor,
    background = LightBackground,
    surface = WhiteColor,
    onSecondary = LightSecondaryBlackColor,
    onSurface = LightSecondaryBlackColor,

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