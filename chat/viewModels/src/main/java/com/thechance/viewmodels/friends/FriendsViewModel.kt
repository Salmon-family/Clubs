package com.thechance.viewmodels.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.usecases.GetFriendsUseCase
import com.thechance.viewmodels.friends.uistate.FriendsUiState
import com.thechance.viewmodels.friends.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val getFriends: GetFriendsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getFriendsList()
    }

    private fun getFriendsList() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, isFail = false) }
                val friends = getFriends()
                _uiState.update { friendsUiState ->
                    friendsUiState.copy(
                        friends = friends.map { it.toUiState() },
                        isLoading = false
                    )
                }
            } catch (throwable: Throwable) {
                _uiState.update { it.copy(isLoading = false, isFail = true) }
            }


        }
    }

}