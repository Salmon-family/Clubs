package com.devfalah.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = DarkPrimaryWhiteColor,
    secondaryVariant = DarkSecondaryWhiteColor,
    secondary = LightTernaryBrandColor,
    background = DarkBackground,
    surface = DarkCardColor,
    onSecondary = DarkTernaryWhiteColor,
    onSurface = DarkQuaternaryWhiteColor,
    onBackground = LightPrimaryGrayColor
)

private val LightColorPalette = lightColors(
    primary = LightPrimaryBrandColor,
    primaryVariant = LightPrimaryBlackColor,
    secondaryVariant = LightSecondaryBlackColor,
    secondary = LightSecondaryBrandColor,
    background = LightBackground,
    surface = WhiteColor,
    onSecondary = LightTernaryBlackColor,
    onSurface = LightQuaternaryBlackColor,
    onBackground = LightPrimaryBrandColor

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
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}