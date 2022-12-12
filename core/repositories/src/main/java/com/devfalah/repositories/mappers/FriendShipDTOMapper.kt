package com.devfalah.repositories.mappers

import com.devfalah.entities.FriendShip
import com.devfalah.repositories.models.FriendshipDTO

fun FriendshipDTO.toEntity(): FriendShip {
    return FriendShip(
        isFriend = isFriend ?: false,
        requestExists = requestExists ?: false
    )
}