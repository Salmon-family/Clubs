package com.thechance.ui.screens.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thechance.ui.composable.*
import com.thechance.ui.screens.conversation.navigateToConversation
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.viewmodels.chats.ChatsViewModel
import com.thechance.viewmodels.chats.uiStates.ChatUiState
import com.thechance.viewmodels.chats.uiStates.ChatsUiState

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
        onCLickBack = {},
        onLoadingMoreChats = viewModel::onLoadingMore,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChatsContent(
    state: ChatsUiState,
    onValueChanged: (String) -> Unit,
    onClickChat: (ChatUiState) -> Unit,
    onCLickBack: () -> Unit,
    onLoadingMoreChats: () -> Unit,
) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        TopBarChats(onCLickBack)
        if (state.isLoading) {
            Loading(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = listState,
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
                        chatUiState.guid
                    }
                ) { chat ->
                    FriendChat(
                        chatUiState = chat,
                        modifier = Modifier.animateItemPlacement(),
                        onClick = onClickChat,
                    )
                }
                item {
                    if (state.isLoadingMore) {
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

        LaunchedEffect(key1 = listState.isScrolledToTheEnd()) {
            if (!state.isLoadingMore && !state.isLastPage && listState.isScrolledToTheEnd()) {
                onLoadingMoreChats()
            }
        }
    }
}

fun LazyListState.isScrolledToTheEnd(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 3
}
