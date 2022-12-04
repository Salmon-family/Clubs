package com.thechance.identity.viewmodel.signup

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
            signupUseCase(
                firstName = state.firstName,
                lastName = "",
                email = state.email,
                reEmail = state.email,
                gender = "female",
                birthdate = "01/10/2001",
                username = state.username,
                password = state.password
            )
        }

    }

    fun onChangeEmail(email: String) {
        _uiState.update { it.copy(email = email) }
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