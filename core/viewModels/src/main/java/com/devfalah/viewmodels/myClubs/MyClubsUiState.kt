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
    val id: Int,
    val title: String,
    val description: String,
    val privacy: String
)

fun Club.toUiState(): ClubsState {
    return ClubsState(
        id = id,
        title = name,
        description = description,
        privacy = privacy
    )
}