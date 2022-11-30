package com.thechance.identity.ui.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun OnBoardingText(
    onBoardingData: String,
    style: TextStyle,
    color: Color
){
    Text(
        text = onBoardingData,
        style = style,
        color = color
    )
}