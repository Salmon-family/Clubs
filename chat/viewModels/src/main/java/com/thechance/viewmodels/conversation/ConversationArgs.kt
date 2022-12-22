package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle

class ConversationArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[USER_ID_ARG]).toString().toInt()
    val friendId: Int = checkNotNull(savedStateHandle[FRIEND_ID_ARG]).toString().toInt()

    companion object {
        const val USER_ID_ARG = "userId"
        const val FRIEND_ID_ARG = "friendId"
    }
}