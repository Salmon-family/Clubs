package com.thechance.ui.screens.chats.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor

@Composable
fun RecentMessage(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = message,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = BlackColor.copy(alpha = 0.6f),
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

}