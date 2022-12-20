package com.devfalah.viewmodels.clubRequests

import androidx.lifecycle.SavedStateHandle


class ClubRequestsArgs(savedStateHandle: SavedStateHandle) {
    val clubID: Int = checkNotNull(savedStateHandle[CLUB_ID_ARG]).toString().toInt()
    val ownerId: Int = checkNotNull(savedStateHandle[OWNER_ID_ARG]).toString().toInt()

    companion object {
        const val CLUB_ID_ARG = "clubId"
        const val OWNER_ID_ARG = "ownerId"

    }
}