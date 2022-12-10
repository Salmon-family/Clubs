package com.thechance.identity.viewmodel.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onChangeUserName(userName: String) {
        _uiState.update { it.copy(userName = userName) }
    }

    fun onChangePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onValidatePassword(): Boolean {
        val state = _uiState.value
        //todo: for use case
        return state.password.isNotEmpty() && state.password.length > 6
    }

    fun onLogin() {
        viewModelScope.launch {
            try {
                val login = loginUseCase(_uiState.value.userName, _uiState.value.password)
                Log.i("userName", login.email)
            } catch (e: Exception) {
                Log.i("error", e.message.toString())
            }
        }
    }
}