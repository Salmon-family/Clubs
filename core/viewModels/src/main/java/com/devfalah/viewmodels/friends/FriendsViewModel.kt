package com.devfalah.viewmodels.friends

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserFriendsUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.RemoveFriendRequestUseCase
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
        getUserID()
    }

    private fun getUserID() {
        viewModelScope.launch {
            try {
                val userId = userId()
                _uiState.update { it.copy(isMyProfile = userId == args.ownerId, id = userId) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun getUserFriends() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val friends = getUserFriendsUseCase(args.ownerId)
                if (friends.friends.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            totalFriends = friends.total,
                            friends = (_uiState.value.friends + friends.friends.toFriendsUIState())
                        )
                    }
                }else{
                    _uiState.update { it.copy(isLoading = false, isPagerEnd = true) }
                }
            } catch (t: Throwable) {
                Log.e("TEST", t.message.toString())
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
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