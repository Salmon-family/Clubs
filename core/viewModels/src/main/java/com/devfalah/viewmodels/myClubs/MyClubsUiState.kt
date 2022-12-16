package com.devfalah.viewmodels.myClubs

import com.devfalah.entities.Club

data class MyClubsUiState(
    val myClubs: List<MyClubsState> = emptyList(),
    val isLoading: Boolean = false
)

data class MyClubsState(
    val title: String,
    val description: String,
    val coverUrl: Any,
    val privacy: String
)

fun Club.toUiState(): MyClubsState {
    return MyClubsState(
        title = title,
        description = description,
        coverUrl = coverUrl,
        privacy = privacy
    )
}