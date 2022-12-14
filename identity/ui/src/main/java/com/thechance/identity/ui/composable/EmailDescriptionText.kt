package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun EmailDescriptionText(
    text1: String,
    text2: String,
    text3: String,
) {

    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface)) {
                append("$text1 ")
            }
            withStyle(style = SpanStyle(color = LightPrimaryBrandColor)) {
                append(text2)
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface)) {
                append(text3)
            }
        },
        style = Typography.subtitle2,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textAlign = TextAlign.Start
    )
}