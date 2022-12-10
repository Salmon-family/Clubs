package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatWithFriendUseCase
import com.nadafeteiha.usecases.ReceiveNotificationUseCase
import com.nadafeteiha.usecases.SendMessageUseCase
import com.thechance.viewmodels.conversation.uiStates.toUiState
import com.thechance.viewmodels.conversation.uiStates.ConversationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
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
        viewModelScope.launch {
            try {
                getMessages(userId, friendId).collect { item ->
                    val message = item.map { it.toUiState() }
                    _uiState.update { it.copy(messages = message, isLoading = false) }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                sendMessageUseCase(args.id, args.friendId, message)
            } catch (e: Exception) {
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
}
