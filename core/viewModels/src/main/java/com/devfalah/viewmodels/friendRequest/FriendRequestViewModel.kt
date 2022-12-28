package com.devfalah.viewmodels.friendRequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.friend.AddFriendRequestUseCase
import com.devfalah.usecases.friend.GetUserFriendRequestsUseCase
import com.devfalah.usecases.friend.RemoveFriendRequestUseCase
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
    private val removeFriendRequestUseCase: RemoveFriendRequestUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendRequestUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                fetchFriendsRequest()
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    private fun fetchFriendsRequest() {
        viewModelScope.launch {
            try {
                val friendRequests =
                    friendRequestsUseCase().listToUserUiState()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        friendRequests = friendRequests
                    )
                }
            } catch (t: Throwable) {
                if (t.message.toString().contains("mapping error", true)) {
                    _uiState.emit(FriendRequestUiState())
                } else {
                    _uiState.update {
                        it.copy(isLoading = false, error = t.message.toString())
                    }
                }

            }
        }
    }

    fun acceptFriendRequest(friendRequestId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, minorError = "") }
            try {
                if (addFriendRequestUseCase(friendRequestId = friendRequestId)
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
                if (removeFriendRequestUseCase(friendRequestId = friendRequestId)) {
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