package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle

class ConversationArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[USER_ID_ARG]).toString().toInt()
    val friendId: Int = checkNotNull(savedStateHandle[FRIEND_ID_ARG]).toString().toInt()
    val friendName: String = checkNotNull(savedStateHandle[FRIEND_NAME_ARG]).toString()
    val friendImage: String = checkNotNull(savedStateHandle[FRIEND_IMAGE_ARG]).toString()

    companion object {
        const val USER_ID_ARG = "userId"
        const val FRIEND_ID_ARG = "friendId"
        const val FRIEND_NAME_ARG = "friendName"
        const val FRIEND_IMAGE_ARG = "friendImage"
    }
}