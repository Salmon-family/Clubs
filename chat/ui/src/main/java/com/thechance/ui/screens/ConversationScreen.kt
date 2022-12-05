package com.thechance.ui.screens

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
import com.thechance.ui.composable.SendTextField
import com.thechance.ui.composable.AppBar
import com.thechance.ui.composable.BackgroundChatScreen
import com.thechance.ui.composable.ListOfChat
import com.thechance.viewmodels.chatWithFriend.conversation.ChatWithFriendViewModel
import com.thechance.viewmodels.chatWithFriend.conversation.uiStates.ChatUIState


@SuppressLint("UnrememberedMutableState")
@Composable
internal fun ConversationScreen(
    viewModel: ChatWithFriendViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    ChatContent(
        state = state,
        messageText = state.message,
        onValueChanged = viewModel::onChanceMessage,
        sendMessage = viewModel::sendMessage,
    )
}

@Composable
fun ChatContent(
    state: ChatUIState,
    messageText: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(state.appBar.userName, state.appBar.icon)
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BackgroundChatScreen()
            ListOfChat(state.messages)
            SendTextField(
                text = messageText,
                onValueChanged = onValueChanged,
                sendMessage = sendMessage,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultChatScreenPreview() {
    ConversationScreen()
}
