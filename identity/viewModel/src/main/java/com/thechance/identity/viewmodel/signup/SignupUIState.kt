package com.thechance.identity.viewmodel.signup


data class UserUIState(
    val birthdate: String = "11/11/2001",
    val email: String = "",
    val reEmail: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "male",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val jobTitle: String = "",
    val errorTypeValue: Int = 0,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false
)