package com.thechance.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

@Composable
fun UserChatTitle(
    chatUiState: ChatUiState,
    modifier: Modifier = Modifier
) {
    Text(
        text = chatUiState.fullName,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = BlackColor,
        modifier = modifier
    )
}