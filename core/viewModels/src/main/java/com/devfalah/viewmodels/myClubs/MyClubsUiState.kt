package com.devfalah.viewmodels.myClubs

import com.devfalah.viewmodels.search.ClubUIState

data class MyClubsUiState(
    val myClubs: List<ClubUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)