package com.thechance.identity.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.AccountValidationUseCase
import com.thechance.identity.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val accountValidationUseCase: AccountValidationUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onLogin() {
        onLoading()
        makeLoginRequest()
    }

    private fun onLoading() {
        _uiState.update { it.copy(isLoading = true, isSuccess = false, errorMessage = "") }
    }

    private fun makeLoginRequest() {
        viewModelScope.launch {
            try {
                val user = loginUseCase(_uiState.value.userName, _uiState.value.password)
                _uiState.update { it.copy(userId = user.guid) }
                onSuccess()
            } catch (t: Throwable) {
                onError(errorMessage = t)
            }
        }
    }

    private fun onSuccess() {
        _uiState.update { it.copy(isSuccess = true, errorMessage = "", isLoading = false) }
    }

    private fun onError(errorMessage: Throwable) {
        _uiState.update {
            it.copy(
                errorMessage = errorMessage.message.toString(),
                isSuccess = false,
                isLoading = false
            )
        }
    }

    fun onChangeUserName(userName: String) {
        _uiState.update { it.copy(userName = userName) }
    }

    fun onChangePassword(password: String) {
        if (!uiState.value.isLoading) {
            _uiState.update { it.copy(password = password) }
        }
    }

    fun onValidatePassword(): Boolean {
        val state = _uiState.value
        return accountValidationUseCase.validatePassword(state.password)
    }
}