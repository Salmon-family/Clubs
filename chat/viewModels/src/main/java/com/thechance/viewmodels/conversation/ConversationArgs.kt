package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle

class ConversationArgs(savedStateHandle: SavedStateHandle) {
    val friendId: Int = checkNotNull(savedStateHandle[FRIEND_ID_ARG]).toString().toInt()

    companion object {
        const val FRIEND_ID_ARG = "friendId"
    }
}