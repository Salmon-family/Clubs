package com.thechance.identity.viewmodel.login

import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.AccountValidationUseCase
import com.thechance.identity.usecases.LoginUseCase
import com.thechance.identity.viewmodel.base.BaseViewModel
import com.thechance.identity.viewmodel.utils.ErrorMessageType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val accountValidationUseCase: AccountValidationUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onLogin() {
        onLoading()
        makeLoginRequest()
    }

    private fun onLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                isSuccess = false,
                errorType = ErrorMessageType.NO_ERROR,
            )
        }
    }

    private fun makeLoginRequest() {
        viewModelScope.launch(Dispatchers.IO) {
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
        _uiState.update {
            it.copy(
                isSuccess = true,
                errorType = ErrorMessageType.NO_ERROR,
                isLoading = false
            )
        }
    }

    private fun onError(errorMessage: Throwable) {
        _uiState.update {
            it.copy(
                errorType = getErrorTypeValue(errorMessage),
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