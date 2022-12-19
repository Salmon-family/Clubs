package com.thechance.identity.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun TextTwoToneColor(
    firstText: String,
    secondText: String,
    navigate: () -> Unit
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.secondaryVariant)) {
                append("$firstText ")
            }
            withStyle(style = SpanStyle(color = LightPrimaryBrandColor)) {
                append(secondText)
            }
        },
        style = Typography.caption,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigate() },
        textAlign = TextAlign.Center
    )
}