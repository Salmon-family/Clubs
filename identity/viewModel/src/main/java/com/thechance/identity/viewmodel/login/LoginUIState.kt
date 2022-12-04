package com.thechance.identity.viewmodel.login

data class LoginUIState(
    val userName: String = "",
    val password: String = "",
    val isSuccess: Boolean = false
)
