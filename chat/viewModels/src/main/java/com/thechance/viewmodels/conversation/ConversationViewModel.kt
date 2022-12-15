package com.thechance.viewmodels.conversation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatWithFriendUseCase
import com.nadafeteiha.usecases.ReceiveNotificationUseCase
import com.nadafeteiha.usecases.SendMessageUseCase
import com.thechance.viewmodels.conversation.uiStates.ConversationUIState
import com.thechance.viewmodels.conversation.uiStates.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMessages: GetChatWithFriendUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val receiveNotification: ReceiveNotificationUseCase,
) :
    ViewModel() {

    private val args = ConversationArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ConversationUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { receiveNotification() }
        getListMessages(args.id, args.friendId)
        _uiState.update {
            it.copy(
                appBar = it.appBar.copy(userName = args.friendName, icon = args.friendImage)
            )
        }
    }

    private fun getListMessages(userId: Int, friendId: Int) {
        refreshMessages(userId, friendId)
        viewModelScope.launch {
            getMessages(friendId).collect {
                _uiState.update { uiState ->
                    uiState.copy(
                        messages = it.map { it.toUiState() },
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                sendMessageUseCase(args.id, args.friendId, message)
            } catch (e: Exception) {
                Log.e("DEVFALAH", e.message.toString())
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }

        }
    }

    fun onChanceMessage(newValue: String) {
        _uiState.update { it.copy(message = newValue) }
    }

    fun onClickSendButton() {
        sendMessage(uiState.value.message)
        _uiState.update { it.copy(message = "") }
    }

    private fun refreshMessages(userId: Int, friendId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val messagesCount = getMessages.refreshMessages(userId, friendId, 1)
                _uiState.update { it.copy(messagesCount = messagesCount) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    fun onLoadingMoreMessages() {
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update { it.copy(isLoading = true) }
            val messagesCountLocally = _uiState.value.messages.count()
            val messagesCount = _uiState.value.messagesCount
            if (messagesCount > messagesCountLocally) {
                try {
                    val nextPage = messagesCountLocally / 10 + 1
                    getMessages.refreshMessages(userID = args.id, args.friendId, nextPage)
                    _uiState.update { it.copy(isLoadingMore = false) }
                } catch (e: Exception) {
                    _uiState.update { it.copy(isLoadingMore = false, error = e.message.toString()) }
                }
            } else {
                _uiState.update { it.copy(isLastPage = true, isLoadingMore = false) }
            }
        }
    }
}
