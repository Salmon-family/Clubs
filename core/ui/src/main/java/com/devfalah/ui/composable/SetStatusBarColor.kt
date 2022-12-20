package com.devfalah.ui.composable

import androidx.compose.ui.graphics.Color
import com.devfalah.ui.theme.LightBackgroundColor
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

fun setStatusBarColor(
    color: Color = LightBackgroundColor,
    darkIcons: Boolean = true,
    systemUIController: SystemUiController
) {
    systemUIController.setStatusBarColor(color, darkIcons = darkIcons)
}