package com.thechance.viewmodels.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatsUseCase
import com.nadafeteiha.usecases.ReceiveNotificationUseCase
import com.nadafeteiha.usecases.SearchForChatsUseCase
import com.thechance.viewmodels.chats.uiStates.ChatsUiState
import com.thechance.viewmodels.chats.uiStates.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNot
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

    init {
        initChats()
        viewModelScope.launch {
            receiveNotificationUseCase()
        }
    }

    private fun initChats() {
        viewModelScope.launch {
            refreshChats()
            getChats().filterNot { it.isEmpty() }.collect { chats ->
                _uiState.update { chatsUiState ->
                    chatsUiState.copy(
                        chats = chats.map { it.toUiState() },
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun refreshChats() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val count = getChats.getChats( 1)
                _uiState.update { it.copy(chatsCount = count, isLoading = false) }
            } catch (e: Exception) {
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

     fun onLoadingMore() {
        _uiState.update { it.copy(isLoadingMore = true) }
        viewModelScope.launch {
            try {
                val isLastPage = getChats.loadingMoreChats(
                    chatsCount = _uiState.value.chatsCount,
                    chatsCountLocally= _uiState.value.chats.size,
                )
                _uiState.update { it.copy(isLoadingMore = false, isLastPage = isLastPage) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isLastPage = true, isLoadingMore = false) }
            }
        }
    }
}

