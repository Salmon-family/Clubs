package com.devfalah.viewmodels.userProfile

import androidx.lifecycle.SavedStateHandle

class ProfileArgs(savedStateHandle: SavedStateHandle) {

    val ownerId: Int = checkNotNull(savedStateHandle[PROFILE_OWNER_ID_ARG]).toString().toInt()

    companion object {
        const val PROFILE_OWNER_ID_ARG = "profileOwnerId"
    }
}