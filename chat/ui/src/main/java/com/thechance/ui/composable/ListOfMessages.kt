package com.thechance.ui.composable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.ui.screens.chat.isScrolledToTheEnd
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.ui.toTime
import com.thechance.viewmodels.conversation.uiStates.ConversationUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListOfChat(
    uiState: ConversationUIState,
    onLoadMoreMessages: () -> Unit,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        reverseLayout = true,
        state = lazyListState,
    ) {
        item {
            SpaceVertical(height = 64)
        }
        items(
            items = uiState.messages,
            key = { chatUiState ->
                chatUiState.messageId
            }
        ) {
            LaunchedEffect(key1 = uiState.isLoadingMore) {
                Log.i("Before Messages", "Loading....")
                if (!uiState.isLoadingMore && !uiState.isLastPage && lazyListState.isScrolledToTheEnd()) {
                    Log.i("Load More Messages", "Loading....")
                    onLoadMoreMessages()
                }
            }
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

        item {
            if (uiState.isLoadingMore) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = LightPrimaryBrandColor,
                    )
                }
            }
        }
    }
}