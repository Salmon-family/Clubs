package com.thechance.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
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
import com.thechance.ui.composables.SearchTextField
import com.thechance.viewmodels.chatWithFriend.ChatsViewModel
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

@Composable
fun ChatsScreen(
    viewModel: ChatsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    ChatsContent(
        chats = state.chats,
        searchText = state.searchText,
        onValueChanged = viewModel::onSearchTextChange
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChatsContent(
    chats: List<ChatUiState>,
    searchText:String,
    onValueChanged: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(28.dp))

        SearchTextField(
            modifier = Modifier
            .padding(horizontal = 16.dp),
            text = searchText,
            onValueChanged = onValueChanged
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                items = chats,
                key = { chatUiState ->
                    chatUiState.guid
                }
            ) { chat ->
                FriendChat(chatUiState = chat, modifier = Modifier.animateItemPlacement())
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewChatsScreen() {
    ChatsScreen()
}