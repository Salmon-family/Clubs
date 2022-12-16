package com.devfalah.repositories.mappers

import com.devfalah.entities.Club
import com.devfalah.repositories.models.group.GroupDTO

fun GroupDTO.toEntity(): Club {
    return Club(
        title = title ?: "",
        description = description ?: "",
        coverUrl = coverurl ?: "",
        privacy = membership ?: "",
    )
}

fun List<GroupDTO>.toEntity(): List<Club> = map { it.toEntity() }