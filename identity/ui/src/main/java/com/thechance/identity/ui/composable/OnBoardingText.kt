package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun OnBoardingText(
    sliderData: String,
    style: TextStyle,
    color: Color
){
    Text(
        modifier = Modifier.padding(start = 24.dp),
        text = sliderData,
        style = style,
        color = color
    )
}