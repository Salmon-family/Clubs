package com.devfalah.viewmodels.clubMembers

import androidx.lifecycle.SavedStateHandle

class ClubMembersArgs (savedStateHandle: SavedStateHandle) {
    val clubId: Int = checkNotNull(savedStateHandle[CLUB_ID]).toString().toInt()

    companion object {
        const val CLUB_ID = "CLUB_ID"
    }
}