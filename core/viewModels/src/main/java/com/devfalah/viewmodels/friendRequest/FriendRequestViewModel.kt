package com.devfalah.viewmodels.friendRequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.AddFriendRequestUseCase
import com.devfalah.usecases.GetUserFriendRequestsUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.RemoveFriendRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendRequestViewModel @Inject constructor(
    private val friendRequestsUseCase: GetUserFriendRequestsUseCase,
    private val addFriendRequestUseCase: AddFriendRequestUseCase,
    val getUser: GetUserIdUseCase,
    private val removeFriendRequestUseCase: RemoveFriendRequestUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendRequestUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(userId = getUser()) }
                fetchFriendsRequest()
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    private fun fetchFriendsRequest() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        friendRequests = friendRequestsUseCase(userID = uiState.value.userId).listToUserUiState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(isLoading = false, error = t.message.toString())
                }
            }
        }
    }

    fun acceptFriendRequest(friendRequestId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, minorError = "") }
            try {
                if (addFriendRequestUseCase(
                        userID = uiState.value.userId,
                        friendRequestId = friendRequestId
                    )
                ) {
                    updateFriendRequestList(friendRequestId)
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(isLoading = false, minorError = t.message.toString())
                }
            }
        }
    }

    fun deniedFriendRequest(friendRequestId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, minorError = "") }
            try {
                if (removeFriendRequestUseCase(
                        userID = uiState.value.userId, friendRequestId = friendRequestId
                    )
                ) {
                    updateFriendRequestList(friendRequestId)
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(isLoading = false, minorError = t.message.toString())
                }
            }
        }
    }

    private fun updateFriendRequestList(friendRequestId: Int) {
        _uiState.update {
            it.copy(friendRequests = _uiState.value.friendRequests
                .filterNot { it.userID == friendRequestId }
            )
        }
    }
}