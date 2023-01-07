package com.thechance.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thechance.ui.parseHtml
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.ui.theme.Typography
import com.thechance.ui.theme.WhiteColor

@Composable
fun SenderMessage(
    text: String,
    dateMessage: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = dateMessage,
                style = Typography.subtitle2,
                color = MaterialTheme.colors.onSecondary,
            )
            SpaceHorizontal(width = 8)
            Text(
                modifier = Modifier
                    .background(
                        color = LightPrimaryBrandColor,
                        shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                text = text.parseHtml(),
                style = Typography.body1,
                textAlign = TextAlign.Start,
                color = WhiteColor,
            )
        }
    }
}
