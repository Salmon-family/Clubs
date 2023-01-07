package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun EmailDescriptionText(
    email: String,
) {

    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface)) {
                append(stringResource(id = R.string.using))
            }
            withStyle(style = SpanStyle(color = LightPrimaryBrandColor)) {
                append(email)
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface)) {
                append(stringResource(id = R.string.to_sign_up))
            }
        },
        style = Typography.subtitle2,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textAlign = TextAlign.Start
    )
}