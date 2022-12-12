package com.thechance.identity.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.thechance.identity.ui.theme.Typography

@Composable
fun TextTwoToneColor(
    text1: String,
    color1: Color,
    text2: String,
    color2: Color,
    navigate: () -> Unit
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = color1)) {
                append("$text1 ")
            }
            withStyle(style = SpanStyle(color = color2)) {
                append(text2)
            }
        },
        style = Typography.caption,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigate() },
        textAlign = TextAlign.Center
    )
}