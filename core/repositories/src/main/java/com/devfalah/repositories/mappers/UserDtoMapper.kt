package com.devfalah.repositories.mappers

import com.devfalah.entities.User
import com.devfalah.repositories.models.UserDTO


fun UserDTO.toEntity(): User {
    return User(
        birthdate = this.birthdate ?: "",
        coverUrl = this.coverUrl ?: "",
        email = this.email ?: "",
        name = this.fullName ?: "",
        title = this.jobTitle ?: "",
        username = this.username ?: "",
        gender = this.gender ?: "",
        id = this.guid ?: 0,
        language = this.language ?: "",
        profileUrl = this.icon?.large ?: "",
        isFriend = false,
        isMyProfile = false,
        isRequestExists = false
    )
}

fun List<UserDTO>.toEntity() = map { it.toEntity() }
