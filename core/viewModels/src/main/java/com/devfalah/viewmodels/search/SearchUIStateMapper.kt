package com.devfalah.viewmodels.search

import com.devfalah.entities.Club


fun Club.toUIState(): ClubSearchResult {
    return ClubSearchResult(
        id = id,
        title = name,
        description = description
    )
}

fun List<Club>.toUIState() = map { it.toUIState() }
