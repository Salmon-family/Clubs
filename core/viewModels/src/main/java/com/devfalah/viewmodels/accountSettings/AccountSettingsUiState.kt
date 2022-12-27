package com.devfalah.viewmodels.accountSettings

import com.devfalah.entities.User
import com.devfalah.entities.UserInformation
import com.devfalah.viewmodels.util.Constants.MAX_PASSWORD_LENGTH

data class AccountSettingsUiState(
    val userId: Int = 0,
    val name: String = "",
    val title: String = "",
    val email: String = "",
    val currentPassword: String = "",
    val newPassword: String = "",
    val isSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)

fun User.toAccountSettingsUiState(): AccountSettingsUiState {
    return AccountSettingsUiState(
        userId = id,
        name = name,
        title = title,
        email = email,
    )
}

fun AccountSettingsUiState.isEditButtonEnabled(): Boolean {
    return email.isNotEmpty()
            && currentPassword.isNotEmpty()
            && currentPassword.length > MAX_PASSWORD_LENGTH
            && (newPassword.length > MAX_PASSWORD_LENGTH || newPassword.isEmpty())
}

fun AccountSettingsUiState.toEntity(): UserInformation {
    return UserInformation(
        id = userId,
        name = name,
        title = title,
        email = email,
        password = currentPassword,
        newPassword = newPassword
    )
}