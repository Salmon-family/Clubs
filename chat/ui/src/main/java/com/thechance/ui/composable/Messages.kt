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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.ui.theme.LightQuaternaryBlackColor
import com.thechance.ui.theme.Typography
import com.thechance.ui.theme.WhiteColor

@Composable
private fun TextMessage(
    text: String,
    date: String,
    color: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .padding(top = 8.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = date,
                style = Typography.subtitle2,
                color = LightQuaternaryBlackColor.copy(alpha = 0.38f))
            SpaceHorizontal(width = 8)
            Text(modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
                text = text,
                style = Typography.body1,
                textAlign = TextAlign.Start,
                color = textColor,
            )
        }
    }
}

@Composable
fun SenderMessage(
    text: String,
    dateMessage: String,
    modifier: Modifier = Modifier,
) {
    TextMessage(
        text = text,
        date = dateMessage,
        color = LightPrimaryBrandColor,
        textColor = WhiteColor,
        modifier = modifier,
    )
}

@Composable
fun ReceiverMessage(
    text: String,
    dateMessage: String,
    modifier: Modifier = Modifier,
) {
    TextMessage(
        text = text,
        date = dateMessage,
        color = MaterialTheme.colors.background,
        textColor = MaterialTheme.colors.onBackground,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultMessagesPreview() {
    ReceiverMessage("Default Text Message Preview", "12:00")
}