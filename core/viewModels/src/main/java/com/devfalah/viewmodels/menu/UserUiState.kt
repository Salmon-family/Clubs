package com.devfalah.viewmodels.menu

data class UserUiState(
    val id: Int = 0,
    val profilePhotoUrl: String = "",
    val language: AppLanguage = AppLanguage.ENGLISH,
    val theme: ThemeType = ThemeType.LIGHT,
    val loading: Boolean = false,
    val error: String = "",
    val logout: Boolean = false,
)

enum class AppLanguage(val value: Int){
    ENGLISH(1),
    ARABIC(2)
}

enum class ThemeType(val value: Int){
    LIGHT(1),
    DARK(2)
}