package com.devfalah.viewmodels.friends

import androidx.lifecycle.SavedStateHandle

class FriendsArgs (savedStateHandle: SavedStateHandle) {
    val ownerId: Int = checkNotNull(savedStateHandle[USER_ID_ARG]).toString().toInt()

    companion object {
        const val USER_ID_ARG = "userId"
    }
}