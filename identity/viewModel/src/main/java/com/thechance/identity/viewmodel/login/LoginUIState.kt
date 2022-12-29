package com.thechance.identity.viewmodel.login

data class LoginUIState(
    val userName: String = "",
    val password: String = "",
    val errorMessage: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val userId: Int = 0
)


fun LoginUIState.isEnabled(): Boolean {
    return !isLoading && password.length > 6
}