package com.devfalah.repositories.mappers.search

import com.devfalah.entities.Search
import com.devfalah.repositories.mappers.toEntity
import com.devfalah.repositories.models.SearchDTO

fun SearchDTO.toEntity(): Search {
    return Search(
        users = users?.map { it.toEntity() } ?: emptyList(),
        groups = groups?.map { it.toEntity() } ?: emptyList()
    )
}