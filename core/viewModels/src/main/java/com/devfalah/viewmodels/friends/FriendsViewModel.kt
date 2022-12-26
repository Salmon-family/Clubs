package com.devfalah.viewmodels.friends

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserFriendsUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.RemoveFriendRequestUseCase
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val getUserFriendsUseCase: GetUserFriendsUseCase,
    val userId: GetUserIdUseCase,
    val removeFriend: RemoveFriendRequestUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = FriendsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(FriendsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        getUser()
        getUserFriends()
    }

    private fun getUser() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val userId = userId()
                _uiState.update { it.copy(isMyProfile = userId == args.ownerId, id = userId) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }
        }
    }

    fun getUserFriends() {
        viewModelScope.launch {
            _uiState.update { it.copy(isPagerLoading = true, minorError = "") }
            try {
                val friends = getUserFriendsUseCase(args.ownerId)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPagerLoading = false,
                        isPagerEnd = (friends.friends.isEmpty() || friends.friends.size < MAX_PAGE_ITEM),
                        totalFriends = friends.total,
                        friends = (_uiState.value.friends + friends.friends.toFriendsUIState())
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        minorError = if (uiState.value.friends.isNotEmpty()) { t.message.toString() } else { "" },
                        isLoading = false,
                        isPagerLoading = false,
                        error = if (uiState.value.friends.isEmpty()) { t.message.toString() } else { "" }
                    )
                }
            }
        }
    }

    fun removeFriend(friendID: Int) {
        viewModelScope.launch {
            try {
                if (removeFriend(uiState.value.id, friendID)) {
                    _uiState.update {
                        it.copy(friends = _uiState.value.friends.filterNot { it.id == friendID })
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }
}