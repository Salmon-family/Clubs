package com.thechance.ui.screens.conversation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thechance.ui.composable.SendTextField
import com.thechance.ui.composable.AppBar
import com.thechance.ui.composable.BackgroundChatScreen
import com.thechance.ui.composable.ListOfChat
import com.thechance.viewmodels.conversation.ConversationViewModel
import com.thechance.viewmodels.conversation.uiStates.ConversationUIState


@SuppressLint("UnrememberedMutableState")
@Composable
internal fun ConversationScreen(
    navController: NavHostController,
    viewModel: ConversationViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    ChatContent(
        state = state,
        messageText = state.message,
        onValueChanged = viewModel::onChanceMessage,
        sendMessage = viewModel::onClickSendButton,
        onCLickBack = {
            navController.popBackStack()
        }
    )
}

@Composable
fun ChatContent(
    state: ConversationUIState,
    messageText: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    onCLickBack: ()->Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(state.appBar.userName, state.appBar.icon,onCLickBack)
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


