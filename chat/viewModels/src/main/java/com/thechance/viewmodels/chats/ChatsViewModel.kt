package com.thechance.viewmodels.chats

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatsUseCase
import com.nadafeteiha.usecases.ReceiveNotificationUseCase
import com.nadafeteiha.usecases.SearchForChatsUseCase
import com.thechance.viewmodels.chats.uiStates.toUiState
import com.thechance.viewmodels.chats.uiStates.ChatsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChats: GetChatsUseCase,
    private val searchForChats: SearchForChatsUseCase,
    private val receiveNotificationUseCase: ReceiveNotificationUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatsUiState())
    val uiState = _uiState.asStateFlow()
    val id = 2

    init {
        initChats(id)
    }

   private fun initChats(userID: Int){
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                getChats(userID).collect { chats ->
                    _uiState.update { chatsUiState ->
                        chatsUiState.copy(
                            chats = chats.map { it.toUiState() }, isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("DEVFALAH",e.message.toString())
                _uiState.update {
                    it.copy(isLoading = false, error = e.message.toString())
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _uiState.update { it.copy(searchText = text) }
        onSearch(text)
    }

    private fun onSearch(searchQuery: String) {
        viewModelScope.launch {
            searchForChats(searchQuery).collect { chats ->
                _uiState.update { chatsUiState ->
                    chatsUiState.copy(chats = chats.map { it.toUiState() })
                }
            }
        }
    }

}
