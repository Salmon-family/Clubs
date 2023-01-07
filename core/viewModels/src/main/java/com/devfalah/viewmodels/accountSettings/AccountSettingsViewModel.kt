package com.devfalah.viewmodels.accountSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.user.EditUserInformationUseCase
import com.devfalah.usecases.user.GetMyAccountProfileDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountSettingsViewModel @Inject constructor(
    private val myAccountProfileDetails: GetMyAccountProfileDetailsUseCase,
    private val editUserInformationUseCase: EditUserInformationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountSettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAccountDetails()
    }

    private fun getAccountDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                _uiState.update { myAccountProfileDetails().toAccountSettingsUiState() }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }
        }
    }


    fun onEmailChange(text: String) {
        _uiState.update { it.copy(email = text) }
    }

    fun onNewPasswordChange(text: String) {
        _uiState.update { it.copy(newPassword = text) }
    }

    fun onCurrentPasswordChange(text: String) {
        _uiState.update { it.copy(currentPassword = text) }
    }


    fun onClickEdit() {
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