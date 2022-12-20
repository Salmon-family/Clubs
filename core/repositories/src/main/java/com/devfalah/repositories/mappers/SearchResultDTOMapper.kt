package com.devfalah.repositories.mappers

import com.devfalah.entities.SearchResult
import com.devfalah.repositories.models.SearchResultDto

fun SearchResultDto.toEntity(): SearchResult {
    return SearchResult(
        clubs = groups?.toEntity() ?: emptyList(),
        users = users?.toEntity() ?: emptyList()
    )
}
