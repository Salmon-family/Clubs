package com.thechance.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimeRecentMessage(
    chatUiState: ChatUiState,
    modifier: Modifier = Modifier
) {
    Text(
        text = convertLongToTime(time = chatUiState.time),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color =
        BlackColor.copy(alpha = 0.6f),
        modifier = modifier
    )
}
private fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    return format.format(date)
}