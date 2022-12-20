package com.devfalah.repositories.mappers

import com.devfalah.entities.Club
import com.devfalah.repositories.models.group.GroupDTO

fun List<GroupDTO>.toEntity(): List<Club> = map { it.toEntity() }

fun GroupDTO.toEntity(): Club {
    return Club(
        id = guid ?: 0,
        name = title ?: "",
        description = description ?: "",
        privacy = membership ?: "1",
    )
}