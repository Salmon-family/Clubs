package com.devfalah.repositories.mappers

import com.devfalah.entities.Friends
import com.devfalah.repositories.models.FriendsDTO

internal fun FriendsDTO.toEntity(): Friends {
    return Friends(
        friends = list?.toEntity() ?: emptyList(),
        total = total ?: 0,
        page = offset ?: 0,
        isMyFriends = false
    )
}