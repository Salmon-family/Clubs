package com.thechance.identity.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.entities.UserData
import com.thechance.identity.usecases.AccountValidationUseCase
import com.thechance.identity.usecases.GetUserIdUseCase
import com.thechance.identity.usecases.SaveUserIdUseCase
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
    private val accountValidationUseCase: AccountValidationUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    fun onSignup() {
        onLoading()
        makeSignupRequest()
    }

    private fun onLoading() {
        _uiState.update { it.copy(isLoading = true, isSuccess = false, errorMessage = "") }
    }

    private fun makeSignupRequest() {
        viewModelScope.launch {
            val state = _uiState.value
            val userData = UserData(
                fullName = state.firstName,
                jobTitle = state.jobTitle,
                fcmToken = "_",
                email = state.email,
                reEmail = state.email,
                gender = state.gender,
                birthdate = state.birthdate,
                username = state.username,
                password = state.password,
            )
            try {
                val userId = signupUseCase(userData)
                saveUserId(userId)
                onSuccess()
            } catch (t: Throwable) {
                onError(errorMessage = t)
            }
        }
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

    private fun saveUserId(id: String) {
        viewModelScope.launch {
            saveUserIdUseCase(id)
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
        val state = _uiState.value
        return accountValidationUseCase.checkPasswordMatching(state.password, state.confirmPassword)
    }

    fun onValidatePassword(): Boolean {
        val state = _uiState.value
        return accountValidationUseCase.validatePassword(state.password)
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
        return accountValidationUseCase.validateName(state.firstName, state.username)
    }

    fun onChangeBirthdate(birthdate: String) {
        _uiState.update { it.copy(birthdate = birthdate) }
    }

    fun onChangeGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

    fun onChangeJobTitle(jobTitle: String){
        _uiState.update { it.copy(jobTitle = jobTitle) }
    }

    fun onValidateJobTitle(): Boolean{
        return accountValidationUseCase.validateJobTitle(_uiState.value.jobTitle)
    }

}