package com.devfalah.viewmodels.userInformation

import com.devfalah.entities.User
import com.devfalah.entities.UserInformation

data class UserInformationUIState(
    val id: Int = 0,
    val name: String = "",
    val title: String = "",
    val email: String = "",
    val password: String = "",
    val isSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)

fun User.toUserInfoUIState(): UserInformationUIState {
    return UserInformationUIState(
        id = id,
        name = name,
        title = title,
        email = email,
    )
}

fun UserInformationUIState.isUpdateInformationButtonEnabled(): Boolean {
    return name.isNotEmpty() && title.isNotEmpty() && password.isNotEmpty() && password.length > 6
}


fun UserInformationUIState.toEntity(): UserInformation {
    return UserInformation(
        id = id,
        name = name,
        title = title,
        email = email,
        password = password
    )
}