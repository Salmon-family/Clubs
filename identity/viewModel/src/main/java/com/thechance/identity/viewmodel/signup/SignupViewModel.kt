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
//        makeSignupRequest()
    }

    fun makeSignupRequest() {
        try {
            viewModelScope.launch {
                val signup = signupUseCase(
                    firstName = "amnahaa",
                    lastName = "amkmx",
                    email = "yo748486@cnmogs.com",
                    reEmail = "yo748486@cnmogs.com",
                    gender = "male",
                    birthdate = "01/10/2001",
                    username = "mahmoudr44444",
                    password = "0987654321"
                )
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