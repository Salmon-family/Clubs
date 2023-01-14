package com.devfalah.repositories.mappers

import com.devfalah.entities.User
import com.devfalah.repositories.models.friends.FriendDTO

internal fun FriendDTO.toEntity(): User {
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
        isRequestExists = false,
        isMyProfile = false,
        token = fcmToken ?: "",
    )
}

internal fun List<FriendDTO>.toEntity(): List<User> = map { it.toEntity() }
