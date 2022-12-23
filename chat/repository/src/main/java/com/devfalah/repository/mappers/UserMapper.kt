package com.devfalah.repository.mappers

import com.devfalah.repository.models.UserDTO
import com.thechance.entities.User


fun UserDTO.toEntity(): User {
    return User(
        id = this.guid?:0,
        name = this.fullname?:"",
        fcmToken = this.fcmToken?:"",
        profileUrl = this.icon?.large ?:""
    )
}