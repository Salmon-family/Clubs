package com.devfalah.viewmodels.search

import com.devfalah.entities.Club


fun Club.toUIState(): ClubUIState {
    return ClubUIState(
        id = id,
        title = name,
        description = description
    )
}

fun List<Club>.toUIState() = map { it.toUIState() }
