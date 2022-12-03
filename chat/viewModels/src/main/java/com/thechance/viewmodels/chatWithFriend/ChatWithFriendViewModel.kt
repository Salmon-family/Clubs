package com.thechance.viewmodels.chatWithFriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatWithFriendUseCase
import com.nadafeteiha.usecases.GetMessagesFromLocalUseCase
import com.nadafeteiha.usecases.SetSendMessageUseCase
import com.thechance.viewmodels.chatWithFriend.mappers.toState
import com.thechance.viewmodels.chatWithFriend.states.ChatUIState
import com.thechance.viewmodels.chatWithFriend.states.MessageUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatWithFriendViewModel @Inject constructor(
    private val getListMessagesUseCase: GetChatWithFriendUseCase,
    private val setSendMessageUseCase: SetSendMessageUseCase,
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(ChatUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getListMessages(7, 10)
    }

    private fun getListMessages(userId: Int, friendId: Int) {
        viewModelScope.launch {
            try {
                getListMessagesUseCase.refreshMessages(userId, friendId)
                getListMessagesUseCase.getMessagesFromLocal().collect { list ->
                    _uiState.update {
                        it.copy(
                            messages = list.map { it.toState() }
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    private fun setSendMessage(message: String) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        message = setSendMessageUseCase(7, 10, message).toState(),
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }

        }
    }

    fun onChanceMessage(newValue: String) {
        _uiState.update {
            it.copy(message = MessageUIState(message = newValue))
        }
    }

    fun sendMessage() {
        setSendMessage(uiState.value.message.message)
        _uiState.update { it.copy(message = MessageUIState(message = "")) }
    }
}
