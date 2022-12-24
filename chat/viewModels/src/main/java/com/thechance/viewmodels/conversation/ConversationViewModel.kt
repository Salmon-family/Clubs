package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatWithFriendUseCase
import com.nadafeteiha.usecases.GetUserDetailUseCase
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
    private val getUserDetail: GetUserDetailUseCase,
) : ViewModel() {

    private val args = ConversationArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ConversationUIState())
    val uiState = _uiState.asStateFlow()

    init {
        receiveNotification()
        getListMessages(args.id, args.friendId)
        getUser()

    }

    private fun receiveNotification() {
        viewModelScope.launch { receiveNotification() }

    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                val friend = getUserDetail(args.friendId)
                _uiState.update {
                    it.copy(
                        appBar = it.appBar.copy(userName = friend.name, icon = friend.profileUrl),
                        fcmToken = friend.fcmToken,
                    )
                }
            } catch (e: Throwable) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    private fun getListMessages(userId: Int, friendId: Int) {
        viewModelScope.launch {
            try {
                refreshMessages(userId, friendId)
                getMessages(friendId).collect {
                    _uiState.update { uiState ->
                        uiState.copy(
                            messages = it.map { it.toUiState() },
                            isLoading = false
                        )
                    }
                }
            }catch (e:Throwable){
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                sendMessageUseCase(args.id, args.friendId, message, _uiState.value.fcmToken)
            } catch (e: Throwable) {
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
                getMessages.refreshMessages(userId, friendId, 2)
                _uiState.update { it.copy(messagesCount = messagesCount) }
            } catch (e: Throwable) {
                _uiState.update {
                    it.copy(error = e.message, isLoading = false)
                }
            }
        }
    }

    fun onLoadingMoreMessages() {
        _uiState.update { it.copy(isLoadingMore = true) }
        viewModelScope.launch {
            try {
                val isLastPage = getMessages.loadingMoreMessages(
                    userID = args.id,
                    friendId = args.friendId,
                    messagesCount = _uiState.value.messagesCount,
                    messagesCountLocally = _uiState.value.messages.size,
                )
                _uiState.update { it.copy(isLoadingMore = false, isLastPage = isLastPage) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isLastPage = true, isLoadingMore = false) }
            }
        }
    }
}
