package com.devfalah.repositories.mappers

import com.devfalah.entities.Members
import com.devfalah.repositories.models.GroupMembersDTO
import com.devfalah.repositories.models.friends.FriendsDTO

internal fun FriendsDTO.toEntity(): Members {
    return Members(
        friends = list?.toEntity() ?: emptyList(),
        total = total ?: 0,
        page = offset ?: 0,
    )
}