package com.thechance.identity.repositories.mappers

import com.thechance.identity.entities.Account
import com.thechance.identity.entities.Icon
import com.thechance.identity.entities.User
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.IconDto
import com.thechance.identity.repositories.models.UserDTO


fun UserDTO.toEntity(): User {
    return User(
        birthdate = this.birthdate ?: "",
        coverUrl = this.coverUrl ?: false,
        email = this.email ?: "",
        firstName = this.firstName ?: "",
        lastName = this.lastName ?: "",
        fullName = this.fullName ?: "",
        username = this.username ?: "",
        gender = this.gender ?: "",
        guid = this.guid ?: 0,
        language = this.language ?: "",
        icon = this.icon?.toEntity() ?: Icon()
    )
}

fun AccountDTO.toEntity(): Account {
    return Account(
        fullName = this.fullName ?: "",
        username = this.username ?: "",
        guid = this.guid ?: 0,
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