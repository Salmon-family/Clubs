package com.devfalah.viewmodels.userInformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.EditUserInformationUseCase
import com.devfalah.usecases.GetMyAccountProfileDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel @Inject constructor(
    val editUserInformationUseCase: EditUserInformationUseCase,
    val getUserAccountDetails: GetMyAccountProfileDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserInformationUIState())
    val uiState = _uiState.asStateFlow()


    init {
        getUserInformation()
    }

    private fun getUserInformation() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                _uiState.update {
                    getUserAccountDetails().toUserInfoUIState()
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }
        }
    }

    fun onNameTextChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onTitleChange(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onPasswordTextChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onClickSave() {
        _uiState.update { it.copy(isLoading = true, error = "") }
        viewModelScope.launch {
            try {
                editUserInformationUseCase(user = uiState.value.toEntity())
                _uiState.update { it.copy(isLoading = false, isSuccessful = true) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }
}