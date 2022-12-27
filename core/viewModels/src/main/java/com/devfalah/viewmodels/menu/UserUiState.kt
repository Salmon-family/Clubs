package com.devfalah.viewmodels.menu

data class UserUiState(
    val userId: Int = 0,
    val profilePhotoUrl: String = "",
    val language: AppLanguage = AppLanguage.ENGLISH,
    val loading: Boolean = false,
    val error: String = "",
    val logout: Boolean = false,
)

enum class AppLanguage(val value: Int){
    ENGLISH(1),
    ARABIC(2)
}