package com.thechance.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thechance.ui.composables.FriendChat
import com.thechance.viewmodels.chatWithFriend.ChatsViewModel
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState


@Composable
fun ChatsScreen(
    viewModel: ChatsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ChatsContent(chats = state.chats)
}

@Composable
private fun ChatsContent(
    chats: List<ChatUiState>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(chats) {
            FriendChat(chatUiState = it)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewChatsScreen() {
    ChatsScreen()
}