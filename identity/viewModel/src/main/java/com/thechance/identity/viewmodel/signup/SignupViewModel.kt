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

    init {
        makeSignupRequest()
    }
    fun makeSignupRequest() {
            viewModelScope.launch {
                signupUseCase(
                    firstName = "amnah",
                    lastName = "aliaa",
                    email = "povayi4188@cnogs.com",
                    reEmail = "povayi4188@cnogs.com",
                    gender = "female",
                    birthdate = "22/01/2001",
                    username = "amnah.ali44",
                    password = "0987654321"
                )
            }
    }

    fun onChangeFullName(fullName: String) {
        _uiState.update { it.copy(firstName = fullName) }
    }

    fun onChangeUserName(userName: String) {
        _uiState.update { it.copy(username = userName) }
    }

}