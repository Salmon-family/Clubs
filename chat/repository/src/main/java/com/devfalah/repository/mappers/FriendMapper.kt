package com.devfalah.repository.mappers

import com.devfalah.repository.models.FriendDTO
import com.devfalah.repository.models.FriendDTOLocal
import com.devfalah.repository.models.FriendsDTO
import com.thechance.entities.Friend
import com.thechance.entities.Friends

fun FriendDTO.toEntity(): Friend {
    return Friend(
        id = guid ?: 0,
        name = fullname ?: "",
        icon = icon?.large ?: "",
        title = jobTitle ?: ""
    )
}

fun FriendsDTO.toEntity(): Friends {
    return Friends(
        friends = list?.map { it.toEntity() } ?: emptyList(),
        count = total ?: 0
    )
}

fun FriendDTOLocal.toEntity(): Friend {
    return Friend(
        id = id,
        name = name,
        icon = icon,
        title = title
    )
}

fun Friend.toLocalDTO(): FriendDTOLocal {
    return FriendDTOLocal(
        id = id,
        name = name,
        icon = icon,
        title = title
    )
}