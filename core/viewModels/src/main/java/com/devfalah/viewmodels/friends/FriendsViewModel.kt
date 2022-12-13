package com.devfalah.viewmodels.friends

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val getUserFriendsUseCase: GetUserFriendsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUIState())
    val uiState = _uiState.asStateFlow()
    private val ownerID = checkNotNull(savedStateHandle["profileId"]).toString().toInt()

    init {
        getUserFriends()
    }

    private fun getUserFriends() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val friends = getUserFriendsUseCase(ownerID)
                _uiState.update { it.copy(isLoading = false, friends = friends.toUIState()) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }
        }
    }
}