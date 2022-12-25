package com.devfalah.viewmodels.myClubs

import com.devfalah.viewmodels.search.ClubUIState

data class MyClubsUiState(
    val id: Int = 0,
    val myClubs: List<ClubUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)