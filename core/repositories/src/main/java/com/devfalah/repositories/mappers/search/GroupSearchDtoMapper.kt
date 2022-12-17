package com.devfalah.repositories.mappers.search

import com.devfalah.entities.Club
import com.devfalah.entities.GroupSearch
import com.devfalah.repositories.models.GroupSearchDTO

fun GroupSearchDTO.toEntity(): Club {
    return Club(
    description = description ?: "" ,
    title = title ?: "",
    coverUrl = "",
    privacy = membership ?: ""
    )
}