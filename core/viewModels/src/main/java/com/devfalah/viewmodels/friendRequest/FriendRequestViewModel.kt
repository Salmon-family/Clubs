package com.devfalah.viewmodels.friendRequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.AddFriendRequestUseCase
import com.devfalah.usecases.GetUserFriendRequestsUseCase
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
    private val removeFriendRequestUseCase: RemoveFriendRequestUseCase
) : ViewModel() {

    private val _friendRequests = MutableStateFlow(FriendRequestUiState(emptyList()))
    val friendRequests = _friendRequests.asStateFlow()

    init {
        fetchFriendsRequest()
    }

    private fun fetchFriendsRequest() {
        viewModelScope.launch {
            _friendRequests.update {
                it.copy(
                    isLoading = false,
                    friendRequests = friendRequestsUseCase(userID = 9).map { friendRequest -> friendRequest.toUIState() }
                )
            }
        }
    }

    fun acceptFriendRequest(friendRequestId: Int) {
        viewModelScope.launch {
            addFriendRequestUseCase(userID = 9, friendRequestId = friendRequestId)
        }
        fetchFriendsRequest()
    }

    fun deniedFriendRequest(friendRequestId: Int) {
        viewModelScope.launch {
            removeFriendRequestUseCase(userID = 9, friendRequestId = friendRequestId)
        }
        fetchFriendsRequest()
    }
}