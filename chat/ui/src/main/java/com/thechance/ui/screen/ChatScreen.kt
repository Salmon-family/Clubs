package com.thechance.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    ChatContent(
        state = state,
        onWriteMessage = viewModel::onChanceMessage,
        sendMessage = viewModel::sendMessage,
    )
}

@Composable
fun ChatContent(
    state: ChatUIState,
    onWriteMessage: (String) -> Unit,
    sendMessage: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar("omar ezzdeen", "")
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BackgroundChatScreen()
            ListOfChat(state.messages)
            CustomTextField(
                information = state.message,
                onTextChange = onWriteMessage,
                onClickAction = sendMessage,
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultChatScreenPreview() {
    ChatScreen()
}
