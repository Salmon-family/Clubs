package com.thechance.identity.viewmodel.login

data class LoginUIState(
    val userName: String = "",
    val password: String = "",
    val isError: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false
)
