package com.devfalah.viewmodels.userProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserAccountDetailsUseCase
import com.devfalah.usecases.GetUserAlbumsUseCase
import com.devfalah.usecases.GetUserFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    val getUserAccountDetails: GetUserAccountDetailsUseCase,
    val getUserAlbumsUseCase: GetUserAlbumsUseCase,
    val getUserFriendsUseCase: GetUserFriendsUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    init {
        val userId = 6
        getUserDetails(userId)
        getUserAlbums(userId)
        getUserFriends(userId)
    }

    private fun getUserDetails(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                _uiState.update {
                    it.copy(
                        loading = false,
                        userDetails = getUserAccountDetails(userID).toUIState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(loading = false, error = t.message.toString()) }
            }
        }
    }

    private fun getUserAlbums(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(albums = getUserAlbumsUseCase(userID).map { it.toUIState() }) }
        }
    }

    private fun getUserFriends(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(friends = getUserFriendsUseCase(userID).map { it.toUIState() }) }
        }
    }
}