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
class LoginPasswordViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginPasswordUIState())
    val uiState = _uiState.asStateFlow()

    private val args = LoginPasswordArgs(savedStateHandle)

    fun onChangePassword(password: String){
        _uiState.update { it.copy(password = password) }
    }

    fun onLogin(){
        try {
            viewModelScope.launch {
                val login = loginUseCase(args.userName, _uiState.value.password)
                Log.i("userName", login.email)
            }
        }catch (e: Exception){
            Log.i("error", e.message.toString())
        }
    }
}