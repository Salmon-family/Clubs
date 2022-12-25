package com.devfalah.viewmodels.myClubs

import com.devfalah.viewmodels.search.ClubUIState

data class MyClubsUiState(
    val id: Int = 0,
    val myClubs: List<ClubUIState> = emptyList(),
    val specialClubs: List<SpecialClubsUIState> = emptyList(),
    val mySpecialClubs: List<SpecialClubsUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class SpecialClubsUIState(
    val id: Int = 0,
    val name: String = "",
    val image: Int = 0
)
