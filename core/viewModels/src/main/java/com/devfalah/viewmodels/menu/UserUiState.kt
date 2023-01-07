package com.devfalah.viewmodels.menu

data class UserUiState(
    val id: Int = -1,
    val profilePhotoUrl: String = "",
    val loading: Boolean = false,
    val error: String = "",
    val logout: Boolean = false,
)