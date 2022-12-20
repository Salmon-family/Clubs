package com.devfalah.viewmodels.clubDetails


data class ClubDetailsUiState(
    val name: String = "",
    val description: String = "",
    val privacy: String = "",
    val membersCount: Int = 0,
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
)

data class MembersUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)