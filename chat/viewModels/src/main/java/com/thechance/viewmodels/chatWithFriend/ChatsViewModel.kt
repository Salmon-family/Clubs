package com.thechance.viewmodels.chatWithFriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatsUseCase
import com.nadafeteiha.usecases.SearchChatsUseCase
import com.thechance.entities.Chat
import com.thechance.viewmodels.chatWithFriend.extensions.convertLongToTime
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState
import com.thechance.viewmodels.chatWithFriend.uistates.ChatsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChats: GetChatsUseCase,
    private val searchChats: SearchChatsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                getChats().collect { chats ->
                    _uiState.update { chatsUiState ->
                        chatsUiState.copy(
                            chats = chats.map { it.toUiState() }, isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message.toString())
                }
            }
        }
    }

    fun onValueChange(searchQuery: String) {
        viewModelScope.launch {
            searchChats(searchQuery).collect { chats ->
                _uiState.update { chatsUiState ->
                    chatsUiState.copy(
                        chats = chats.map { it.toUiState() }
                    )
                }
            }
        }
    }
}

fun Chat.toUiState(): ChatUiState {
    return ChatUiState(
        fullName,
        guid,
        icon,
        time.convertLongToTime(),
        recentMessage
    )
}