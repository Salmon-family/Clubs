package com.thechance.ui.screens.chats.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor

@Composable
fun UserChatTitle(
    userName: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = userName,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = BlackColor,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = modifier
    )
}