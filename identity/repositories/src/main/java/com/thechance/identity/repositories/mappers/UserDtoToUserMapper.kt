package com.thechance.identity.repositories.mappers

import com.thechance.identity.entities.Account
import com.thechance.identity.entities.User
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDTO
import com.thechance.identity.repositories.models.UserDataDTO

fun AccountDTO.toEntity(): Account {
    return Account(
        fullName = this.fullName ?: "",
        username = this.username ?: "",
        guid = this.guid ?: 0,
    )
}

fun UserDTO.toEntity(): User {
    return User(
        birthdate = birthdate ?: "",
        coverUrl = coverUrl ?: false,
        email = email ?: "",
        jobTitle = jobTitle ?: "",
        fcmToken = fcmToken,
        fullName = fullName ?: "",
        username = username ?: "",
        gender = gender ?: "",
        guid = guid ?: 0,
        language = language ?: "",
    )
}
