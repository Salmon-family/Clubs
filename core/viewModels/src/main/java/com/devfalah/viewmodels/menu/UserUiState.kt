package com.devfalah.viewmodels.menu

data class UserUiState(
    val userId: Int = 0,
    val profilePhotoUrl: String = "",
    val loading: Boolean = false,
    val error: String = "",
    val logout: Boolean = false,
)