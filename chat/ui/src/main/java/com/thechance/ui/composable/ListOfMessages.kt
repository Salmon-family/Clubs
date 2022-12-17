package com.thechance.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thechance.ui.toTime
import com.thechance.viewmodels.conversation.uiStates.MessageUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListOfChat(
    messages: List<MessageUIState>,
) {
    LazyColumn(
        reverseLayout = true,
    ) {
        item {
            SpaceVertical(height = 64)
        }
        items(
            items = messages,
            key = { chatUiState ->
                chatUiState.messageId
            }
        ) {
            if (it.isFromUser) {
                SenderMessage(it.message,
                    it.messageDate.toTime(),
                    Modifier.animateItemPlacement())
            } else {
                ReceiverMessage(it.message,
                    it.messageDate.toTime(),
                    Modifier.animateItemPlacement())
            }

        }
    }
}