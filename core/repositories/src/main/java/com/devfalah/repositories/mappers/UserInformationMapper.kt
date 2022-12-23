package com.devfalah.repositories.mappers

import com.devfalah.entities.UserInformation
import com.devfalah.repositories.models.UserInfo

fun UserInformation.toUserInfo(): UserInfo {
    return UserInfo(
        id = id,
        name = name,
        title = title,
        email = email,
        password = password
    )
}