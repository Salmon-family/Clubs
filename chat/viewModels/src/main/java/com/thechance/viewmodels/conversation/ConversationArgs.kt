package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle

class ConversationArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[ID_ARG]).toString().toInt()

    companion object {
        const val ID_ARG = "id"
    }
}