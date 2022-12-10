package com.thechance.identity.viewmodel.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.entities.UserData
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
            val userData = UserData(
                firstname = "state firstName",
                lastname = "_",
                email = "stateemail@gmail.com",
                reEmail = "stateemail@gmail.com",
                gender = state.gender,
                birthdate = state.birthdate,
                username = "state.username",
                password = "state.password",
            )
            try {
                val sign = signupUseCase(userData)
                Log.i("Guid", sign.guid.toString())
            } catch (t: Throwable) {
                _uiState.update { it.copy(isError = t.message.toString()) }
                Log.e("Test", t.message.toString())
            }
        }
    }

    fun onChangeEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun checkIfGmailOrAnotherType(email: String): Boolean {
        return email.isEmailValid()
    }

    private fun String.isEmailValid(): Boolean {
        return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun onChangePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onChangeConfirmPassword(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
    }

    fun onConfirmPassword(): Boolean {
        return (_uiState.value.password == _uiState.value.confirmPassword) &&
                (_uiState.value.password != "") && (_uiState.value.confirmPassword != "")
    }

    fun onValidatePassword(): Boolean {
        val state = _uiState.value
        return state.password.length > 6
                && state.confirmPassword.length > 6
                && onConfirmPassword()
    }

    fun onChangeFullName(fullName: String) {
        _uiState.update { it.copy(firstName = fullName) }
    }

    fun onChangeUserName(userName: String) {
        _uiState.update { it.copy(username = userName) }
    }

    fun onValidateName(): Boolean {
        val state = _uiState.value
        return state.firstName.isNotEmpty() && state.username.isNotEmpty()
    }

    fun onChangeBirthdate(birthdate: String) {
        _uiState.update { it.copy(birthdate = birthdate) }
    }

    fun onChangeGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

}