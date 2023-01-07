package com.thechance.identity.viewmodel.clubs

data class ClubsUIState(
    val clubs: List<ClubUIState> = emptyList(),
    val selectedClubs: List<ClubUIState> = emptyList(),
    val errorMessage: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
)
