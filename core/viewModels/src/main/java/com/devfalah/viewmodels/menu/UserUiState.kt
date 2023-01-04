package com.devfalah.viewmodels.menu

data class UserUiState(
    val id: Int = -1,
    val profilePhotoUrl: String = "",
    val language: AppLanguage = AppLanguage.ENGLISH,
    val theme: ThemeType = ThemeType.LIGHT,
    val loading: Boolean = false,
    val error: String = "",
    val logout: Boolean = false,
)

enum class AppLanguage(val value: String){
    ENGLISH("en"),
    ARABIC("ar")
}

enum class ThemeType(val value: Int){
    LIGHT(1),
    DARK(2)
}