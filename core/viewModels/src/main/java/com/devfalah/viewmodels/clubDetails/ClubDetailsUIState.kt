package com.devfalah.viewmodels.clubDetails


data class ClubDetailsUiState(
    val name: String = "",
    val description: String = "",
    val privacy: String = "",
    val membersCount: Int = 0,
    val postCount: Int = 0,
    val members: List<MembersUIState> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String = ""
)

data class MembersUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)