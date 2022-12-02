package com.thechance.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

@Composable
fun RecentMessage(
    chatItem: ChatUiState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = if (chatItem.recentMessage.length > 50) {
            chatItem.recentMessage.substring(0, 50) + "..."
        } else {
            chatItem.recentMessage
        },
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = BlackColor.copy(alpha = 0.6f),
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}