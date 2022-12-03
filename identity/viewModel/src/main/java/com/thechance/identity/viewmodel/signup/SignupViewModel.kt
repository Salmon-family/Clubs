package com.thechance.identity.viewmodel.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    init {
        makeSignupRequest()
    }

    fun makeSignupRequest() {
        try {
            viewModelScope.launch {
                val signup = signupUseCase(
                    firstName = "amnah",
                    lastName = "ali",
                    email = "yoyey48486@cnogs.com",
                    reEmail = "yoyey48486@cnogs.com",
                    gender = "male",
                    birthdate = "01/10/2001",
                    username = "a4mmm",
                    password = "0987654321"
                )

                Log.i("SIGNUP_PAYLOAD", signup.toString())
            }
        } catch (e: Exception) {
            Log.i("SIGNUP_ERROR", e.message.toString())
            _uiState.update { it.copy(isError = true) }
        }
    }

    fun onChangeFullName(fullName: String) {
        _uiState.update { it.copy(firstName = fullName) }
    }

    fun onChangeUserName(userName: String) {
        _uiState.update { it.copy(username = userName) }
    }

}