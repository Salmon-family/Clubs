package com.devfalah.viewmodels.myClubs

import com.devfalah.entities.Club

data class MyClubsUiState(
    val myClubs: List<ClubsState> = emptyList(),
    val isLoading: Boolean = false
)

data class ClubsUiState(
    val clubs: List<ClubsState> = emptyList(),
    val isLoading: Boolean = false
)

data class ClubsState(
    val title: String,
    val description: String,
    val coverUrl: Any,
    val privacy: String
)

fun Club.toUiState(): ClubsState {
    return ClubsState(
        title = title,
        description = description,
        coverUrl = coverUrl,
        privacy = privacy
    )
}