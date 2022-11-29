package com.thechance.viewmodels.chatWithFriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatWithFriendUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatWithFriendViewModel @Inject constructor(private val getChat: GetChatWithFriendUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { getChat(6, 3).toString() }
        }
    }
}