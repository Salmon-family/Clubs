package com.devfalah.viewmodels.clubDetails

import com.devfalah.viewmodels.clubCreation.ClubPrivacy


data class ClubDetailsUiState(
    val name: String = "",
    val description: String = "",
    val privacy: ClubPrivacy = ClubPrivacy.PUBLIC,
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
)

enum class ClubDetailsPrivacy(val value: Int) {
    PUBLIC(2),
    PRIVATE(1),
}

data class MembersUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)