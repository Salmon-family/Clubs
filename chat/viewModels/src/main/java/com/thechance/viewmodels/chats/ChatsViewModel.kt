package com.thechance.viewmodels.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetChatsUseCase
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatsUiState())
    val uiState = _uiState.asStateFlow()
    val id = 10


    init {
        initChats(id)
    }

    private fun initChats(userID: Int) {
        viewModelScope.launch {
            refreshChats(userID)
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

    private fun refreshChats(userID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val count = getChats.getChats(userID, 1)
                _uiState.update { it.copy(chatsCount = count) }
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
                    userID = id,
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

