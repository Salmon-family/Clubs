package com.devfalah.viewmodels.clubDetails

import androidx.lifecycle.SavedStateHandle

class ClubDetailsArgs(savedStateHandle: SavedStateHandle) {
    val groupId: Int = checkNotNull(savedStateHandle[GROUP_ID_ARG]).toString().toInt()

    companion object {
        const val GROUP_ID_ARG = "groupId"
    }
}