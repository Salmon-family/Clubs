package com.devfalah.viewmodels.friends

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.friend.GetUserFriendsUseCase
import com.devfalah.usecases.friend.RemoveFriendRequestUseCase
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
    val removeFriendUseCase: RemoveFriendRequestUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = FriendsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(FriendsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        getUserFriends()
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
                        isMyProfile = friends.isMyFriends,
                        friends = (_uiState.value.friends + friends.friends.toFriendsUIState())
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        minorError = if (uiState.value.friends.isNotEmpty()) {
                            t.message.toString()
                        } else {
                            ""
                        },
                        isLoading = false,
                        isPagerLoading = false,
                        error = if (uiState.value.friends.isEmpty()) {
                            t.message.toString()
                        } else {
                            ""
                        }
                    )
                }
            }
        }
    }

    fun removeFriend(friendID: Int) {
        viewModelScope.launch {
            try {
                if (removeFriendUseCase(friendID)) {
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