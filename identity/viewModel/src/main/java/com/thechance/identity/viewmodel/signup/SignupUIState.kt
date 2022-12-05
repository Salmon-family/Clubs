package com.thechance.identity.viewmodel.signup

data class UserUIState(
    val birthdate: String = "",
    val email: String = "",
    val reEmail: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = true,
    val error: String = "" ,
)