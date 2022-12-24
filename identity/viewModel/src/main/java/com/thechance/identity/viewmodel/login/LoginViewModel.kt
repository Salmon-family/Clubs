package com.thechance.identity.viewmodel.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.entities.UserData
import com.thechance.identity.usecases.AccountValidationUseCase
import com.thechance.identity.usecases.GetFcmTokenUseCase
import com.thechance.identity.usecases.LoginUseCase
import com.thechance.identity.usecases.UpdateFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val accountValidationUseCase: AccountValidationUseCase,
    private val updateFcmTokenUseCase: UpdateFcmTokenUseCase,
    private val getFcmTokenUseCase: GetFcmTokenUseCase
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
                withContext(Dispatchers.IO){
                    val login = loginUseCase(_uiState.value.userName, _uiState.value.password)
                    val userData = UserData(
                        userId = login.guid,
                        newEmail = login.email,
                        newGender = login.gender,
                        currentPassword = _uiState.value.password,
                        newFullName = login.fullName,
                        newFcmToken = getFcmToken(),
                        newJobTitle = login.jobTitle
                    )
                    Log.i("userName", login.username)
                    updateFcmTokenUseCase(userData)
                }
                onSuccess()
            } catch (t: Throwable) {
                onError(errorMessage = t)
            }
        }
    }

    private fun getFcmToken(): String{
        return getFcmTokenUseCase()
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
        _uiState.update { it.copy(password = password) }
    }

    fun onValidatePassword(): Boolean {
        val state = _uiState.value
        return accountValidationUseCase.validatePassword(state.password)
    }
}