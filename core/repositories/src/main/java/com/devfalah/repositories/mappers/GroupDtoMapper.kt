package com.devfalah.repositories.mappers

import com.devfalah.entities.Group
import com.devfalah.repositories.models.group.GroupDTO

fun GroupDTO.toEntity(): Group {
    return Group(
        title = title ?: "",
        description = description ?: "",
        coverUrl = coverurl ?: "",
        privacy = membership ?: "",
    )
}

fun List<GroupDTO>.toEntity(): List<Group> = map { it.toEntity() }