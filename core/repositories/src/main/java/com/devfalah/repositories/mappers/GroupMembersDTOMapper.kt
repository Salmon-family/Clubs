package com.devfalah.repositories.mappers

import com.devfalah.entities.Members
import com.devfalah.repositories.models.GroupMembersDTO

internal fun GroupMembersDTO.toEntity(): Members {
    return Members(
        friends = members?.toEntity() ?: emptyList(),
        total = total ?: 0,
        page = offset ?: 0,
    )
}