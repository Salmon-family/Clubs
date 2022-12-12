package com.devfalah.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.devfalah.ui.theme.LightBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarColor(
    color: Color = LightBackgroundColor,
    darkIcons: Boolean = true
) {
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color, darkIcons = darkIcons)

}