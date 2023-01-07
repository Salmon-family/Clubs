package com.devfalah.viewmodels.clubRequests

import androidx.lifecycle.SavedStateHandle


class ClubRequestsArgs(savedStateHandle: SavedStateHandle) {
    val clubID: Int = checkNotNull(savedStateHandle[CLUB_ID_ARG]).toString().toInt()
    val ownerId = checkNotNull(savedStateHandle[CLUB_OWNER_ID_ARG]).toString().toInt()

    companion object {
        const val CLUB_ID_ARG = "clubId"
        const val CLUB_OWNER_ID_ARG = "club_owner_Id"

    }
}