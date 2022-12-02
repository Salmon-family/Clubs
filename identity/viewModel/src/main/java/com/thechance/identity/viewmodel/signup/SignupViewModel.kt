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

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    fun makeSignupRequest() {
        viewModelScope.launch {
            val user = signupUseCase(
                firstName = "firstName",
                lastName = "lastName",
                email = "email",
                reEmail = "reEmail",
                gender = "gender",
                birthdate = "birthdate",
                username = "username",
                password = "password"
            )
            _uiState.update { user.toString() }
        }
    }

}