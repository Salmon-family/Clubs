package com.devfalah.viewmodels.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserAccountDetailsUseCase
import com.devfalah.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    val getUserId: GetUserIdUseCase,
    val getUserAccountDetailsUseCase: GetUserAccountDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                _uiState.update { it.copy(userId = getUserId()) }
                getUserPhotoUrl(_uiState.value.userId)
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    private fun getUserPhotoUrl(userId: Int) {
        viewModelScope.launch {
            val userPhotoUrl = getUserAccountDetailsUseCase(userId, userId).profileUrl
            try {
                _uiState.update { it.copy(profilePhotoUrl = userPhotoUrl) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

}