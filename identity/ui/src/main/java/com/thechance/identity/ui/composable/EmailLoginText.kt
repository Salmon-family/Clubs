package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.InputText
import com.thechance.identity.ui.theme.Typography

@Composable
fun EmailLoginText(
    text1: String,
    color1: Color,
    text2: String,
    color2: Color,
    text3: String,
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = color1)) {
                append("$text1 ")
            }
            withStyle(style = SpanStyle(color = color2)) {
                append("$text2 ")
            }
            withStyle(style = SpanStyle(color = color1)) {
                append(text3)
            }
        },
        style = Typography.InputText,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
        textAlign = TextAlign.Start
    )
}