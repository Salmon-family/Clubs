package com.devfalah.viewmodels.editclub

import androidx.lifecycle.SavedStateHandle

class EditClubArgs(savedStateHandle: SavedStateHandle) {
    val clubId: Int = checkNotNull(savedStateHandle[CLUB_ID_ARG]).toString().toInt()
    val clubName: String = checkNotNull(savedStateHandle[CLUB_NAME_ARG]).toString()
    val privacyType: Boolean = checkNotNull(savedStateHandle[PRIVACY_ARG])
    val clubDescription: String = checkNotNull(savedStateHandle[CLUB_DESCRIPTION]).toString()

    companion object {
        const val CLUB_ID_ARG = "clubId"
        const val CLUB_NAME_ARG = "clubName"
        const val PRIVACY_ARG = "privacyType"
        const val CLUB_DESCRIPTION = "clubDescription"
    }
}