package com.thechance.repository.mapper

import com.thechance.entity.User
import com.thechance.repository.domainModel.UserDTO

fun UserDTO.map(): User {
    return User(
        fullName = fullname ?: "",
        userID = guid ?: 0,
        birthdate = birthdate ?: "",
        email = email ?: "",
        gender = gender ?: ""
    )
}
