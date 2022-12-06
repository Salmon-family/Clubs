package com.thechance.ui.screens.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thechance.ui.composable.FriendChat
import com.thechance.ui.composable.Loading
import com.thechance.ui.composable.SearchTextField
import com.thechance.ui.composable.TopBarChats
import com.thechance.ui.screens.conversation.navigateToConversation
import com.thechance.ui.theme.BlackColor
import com.thechance.viewmodels.chatWithFriend.ChatsViewModel
import com.thechance.viewmodels.chatWithFriend.uiStates.ChatUiState
import com.thechance.viewmodels.chatWithFriend.uiStates.ChatsUiState

@Composable
fun ChatsScreen(
    navController: NavHostController,
    viewModel: ChatsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    ChatsContent(
        state = state,
        onValueChanged = viewModel::onSearchTextChange,
        onClickChat = {
            navController.navigateToConversation(id = viewModel.id,
                friendId = it.guid,
                friendName = it.fullName,
                friendImage = it.icon)
        },
        onCLickBack = {}
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChatsContent(
    state: ChatsUiState,
    onValueChanged: (String) -> Unit,
    onClickChat: (ChatUiState) -> Unit,
    onCLickBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopAppBar(
            backgroundColor = Color.White,
            contentColor = BlackColor,
            elevation = 0.dp
        ) {
            TopBarChats(onCLickBack)
        }
        if (state.isLoading) {
            Loading(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    SearchTextField(
                        text = state.searchText,
                        onValueChanged = onValueChanged
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(
                    items = state.chats,
                    key = { chatUiState ->
                        chatUiState.fullName
                    }
                ) { chat ->
                    FriendChat(
                        chatUiState = chat,
                        modifier = Modifier.animateItemPlacement(),
                        onClick = onClickChat,
                    )
                }
            }
        }
    }
}

