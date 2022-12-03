package com.devfalah.repositories.mappers

import com.devfalah.entities.Icon
import com.devfalah.entities.User
import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.IconDto
import com.devfalah.repositories.models.UserDTO

fun FriendDTO.toEntity(): User {
    return User(
        birthdate = this.birthdate ?: "",
        coverUrl = false,
        email = this.email ?: "",
        firstName = this.firstName ?: "",
        lastName = this.lastName ?: "",
        fullName = this.fullname ?: "",
        username = this.username ?: "",
        gender = this.gender ?: "",
        guid = this.guid ?: 0,
        language = this.language ?: "",
        icon = this.icon?.toEntity() ?: Icon()
    )
}

fun IconDto.toEntity(): Icon {
    return Icon(
        small = this.small ?: "",
        smaller = this.smaller ?: "",
        large = this.large ?: "",
        larger = this.larger ?: "",
        topBar = this.topbar ?: "",
    )
}