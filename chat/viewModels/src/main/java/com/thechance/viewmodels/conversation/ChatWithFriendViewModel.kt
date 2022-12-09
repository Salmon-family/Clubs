package com.thechance.viewmodels.conversation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatWithFriendUseCase
import com.nadafeteiha.usecases.ReceiveNotificationUseCase
import com.nadafeteiha.usecases.SetSendMessageUseCase
import com.thechance.viewmodels.conversation.uiMappers.toMessage
import com.thechance.viewmodels.conversation.uiStates.ChatUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatWithFriendViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListMessagesUseCase: GetChatWithFriendUseCase,
    private val setSendMessageUseCase: SetSendMessageUseCase,
    private val receiveNotificationUseCase: ReceiveNotificationUseCase,
    ) :
    ViewModel() {

    private val args = ConversationArgs(savedStateHandle)
    private val userId = args.id
    private val friendId = args.friendId
    private val friendName = args.friendName
    private val friendImage = args.friendImage

    private val _uiState = MutableStateFlow(ChatUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {receiveNotificationUseCase() }
        getListMessages(userId, friendId)
        _uiState.update {
            it.copy(
                appBar = it.appBar.copy(userName = friendName, icon = friendImage)
            )
        }
    }

    private fun getListMessages(userId: Int, friendId: Int) {
        viewModelScope.launch {
            try {
                getListMessagesUseCase(userId, friendId).collect { item ->
                    val message = item.map { it.toMessage() }
                    _uiState.update { it.copy(messages = message, isLoading = false) }
                }
            } catch (e: Exception) {
                Log.e("DEVFALAH",e.message.toString())
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    private fun setSendMessage(message: String) {
        viewModelScope.launch {
            try {
                setSendMessageUseCase(userId, friendId, message)
            } catch (e: Exception) {
                Log.e("DEVFALAHMESSAGE",e.message.toString())
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }

        }
    }

    fun onChanceMessage(newValue: String) {
        _uiState.update { it.copy(message = newValue) }
    }

    fun sendMessage() {
        setSendMessage(uiState.value.message)
        _uiState.update { it.copy(message = "") }
    }
}
