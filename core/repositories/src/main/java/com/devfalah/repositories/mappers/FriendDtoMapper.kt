package com.devfalah.repositories.mappers

import com.devfalah.entities.Icon
import com.devfalah.entities.User
import com.devfalah.repositories.models.FriendDTO


fun FriendDTO.toEntity(): User {
    return User(
        birthdate = this.birthdate ?: "",
        coverUrl = this.coverUrl ?: "",
        email = this.email ?: "",
        name = this.firstName ?: "",
        title = this.lastName ?: "",
        description = this.fullname ?: "",
        username = this.username ?: "",
        gender = this.gender ?: "",
        userID = this.guid ?: 0,
        language = this.language ?: "",
        icon = this.icon?.toEntity() ?: Icon()
    )
}

