package com.thechance.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.thechance.ui.components.CustomTextField
import com.thechance.ui.composable.AppBar
import com.thechance.ui.composable.BackgroundChatScreen
import com.thechance.ui.composable.ListOfChat
import com.thechance.viewmodels.chatWithFriend.ChatWithFriendViewModel
import com.thechance.viewmodels.chatWithFriend.states.ChatUIState

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun ChatScreen(
    viewModel: ChatWithFriendViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val textEmpty: Boolean by derivedStateOf { state.message.message.isEmpty() }
    ChatContent(
        state = state,
        textEmpty = textEmpty,
        onWriteMessage = viewModel::onChanceMessage,
        sendMessage = viewModel::sendMessage,
    )
}

@Composable
fun ChatContent(
    state: ChatUIState,
    textEmpty: Boolean ,
    onWriteMessage: (String) -> Unit,
    sendMessage: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(state.appBar.userName, state.appBar.icon)
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BackgroundChatScreen()
            ListOfChat(state)
            CustomTextField(
                information = state.message.message,
                onTextChange = onWriteMessage,
                empty = textEmpty,
                onClickAction = {
                    if (!textEmpty) {
                        sendMessage()
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultChatScreenPreview() {
    ChatScreen()
}
