package com.devfalah.viewmodels.clubDetails

import androidx.lifecycle.SavedStateHandle

class ClubDetailsArgs(savedStateHandle: SavedStateHandle) {
    val userID: Int = checkNotNull(savedStateHandle[USER_ID_ARG]).toString().toInt()
    val groupId: Int = checkNotNull(savedStateHandle[GROUP_ID_ARG]).toString().toInt()

    companion object {
        const val USER_ID_ARG = "clubId"
        const val GROUP_ID_ARG = "groupId"
    }
}