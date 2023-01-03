package com.thechance.viewmodels.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetFriendsUseCase
import com.nadafeteiha.usecases.SearchForFriendsUseCase
import com.thechance.viewmodels.friends.uistate.FriendsUiState
import com.thechance.viewmodels.friends.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val getFriends: GetFriendsUseCase,
    private val searchForFriends: SearchForFriendsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        initFriends()
    }

    private fun initFriends() {
        viewModelScope.launch {
            refreshChats()
            getFriends().filterNot { it.isEmpty() }.collect { friends ->
                _uiState.update { friendsUiState ->
                    friendsUiState.copy(
                        friends = friends.map { it.toUiState() },
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
                val count = getFriends.getFriends(1)
                _uiState.update { it.copy(friendsCount = count, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, isFail = true)
                }
            }
        }
    }

    fun onSearchTextChanged(searchQuery: String) {
        _uiState.update { it.copy(searchQuery = searchQuery) }
        onSearch(searchQuery)
    }

    private fun onSearch(searchQuery: String) {
        viewModelScope.launch {
            searchForFriends(searchQuery).collect { friends ->
                _uiState.update { friendsUiState ->
                    friendsUiState.copy(
                        friends = friends.map { it.toUiState() },
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onLoadingMoreFriends() {
        _uiState.update { it.copy(isLoadingMore = true) }
        viewModelScope.launch {
            try {
                val isLastPage = getFriends.loadingMoreFriends(
                    friendsCount = _uiState.value.friendsCount,
                    friendsCountLocally = _uiState.value.friends.size,
                )
                _uiState.update { it.copy(isLoadingMore = false, isLastPage = isLastPage) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isLastPage = true, isLoadingMore = false) }
            }
        }
    }
}