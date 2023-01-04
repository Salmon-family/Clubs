package com.thechance.ui.screens.chat

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thechance.ui.composable.FriendChat
import com.thechance.ui.composable.Loading
import com.thechance.ui.composable.SearchTextField
import com.thechance.ui.composable.TopBarChats
import com.thechance.ui.screens.chat.composable.EmptyChatItem
import com.thechance.ui.screens.conversation.navigateToConversation
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.viewmodels.chats.ChatsViewModel
import com.thechance.viewmodels.chats.uiStates.ChatUiState
import com.thechance.viewmodels.chats.uiStates.ChatsUiState
import com.thechance.viewmodels.chats.uiStates.isEmpty

@Composable
fun ChatsScreen(
    navController: NavHostController,
    viewModel: ChatsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val activity = (LocalContext.current as? Activity)
    val systemUIController = rememberSystemUiController()
    ChatsContent(
        state = state,
        onValueChanged = viewModel::onSearchTextChange,
        onClickChat = {
            navController.navigateToConversation(
                friendId = it.guid,
            )
        },
        onCLickBack = {
            activity?.finish()
        },
        onLoadingMoreChats = viewModel::onLoadingMore,
    )
    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        systemUIController.setStatusBarColor(color)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    Scaffold(topBar = { TopBarChats(onCLickBack) }) {
        if (state.isLoading) {
            Loading()
        }
        if (state.isEmpty() && !state.isLoading) {
            EmptyChatItem()
        }
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

fun LazyListState.isScrolledToTheEnd(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 3
}
