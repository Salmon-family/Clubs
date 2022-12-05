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


    fun makeSignupRequest() {
        viewModelScope.launch {
            val state = _uiState.value
            try {
                signupUseCase(
                    firstName = state.firstName,
                    lastName = "",
                    email = "test.test9876@gmail.com",
                    reEmail = "test.test9876@gmail.com",
                    gender = "female",
                    birthdate = "01/10/2001",
                    username = "test.test9876",
                    password = "123456"
                )
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
                Log.e("Test", t.message.toString())
            }
        }

    }

    fun onChangeEmail(email: String) {
        _uiState.update { it.copy(email = email) }
        Log.i("llllllllll", _uiState.value.email)
    }

    fun onChangePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onChangeConfirmPassword(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
    }

    fun onChangeFullName(fullName: String) {
        _uiState.update { it.copy(firstName = fullName) }
    }

    fun onChangeUserName(userName: String) {
        _uiState.update { it.copy(username = userName) }
    }

    fun onChangeBirthdate(birthdate: String) {
        _uiState.update { it.copy(birthdate = birthdate) }
    }

    fun onChangeGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }
}