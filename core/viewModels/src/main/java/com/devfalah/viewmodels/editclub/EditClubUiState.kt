package com.devfalah.viewmodels.editclub

import com.devfalah.viewmodels.clubCreation.ClubPrivacy

data class EditClubUiState(
    val clubId: Int = 0,
    val clubName: String = "",
    val privacy: ClubPrivacy = ClubPrivacy.PUBLIC,
    val clubDescription: String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false
)

fun EditClubUiState.isCreateClubButtonEnabled() = clubName.isNotEmpty() && clubDescription.isNotEmpty()