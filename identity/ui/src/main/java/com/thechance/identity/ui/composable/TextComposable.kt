package com.thechance.identity.ui.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun TextComposable(
    text: String,
    style: TextStyle,
    color: Color,
    modifier: Modifier
){
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color
    )
}