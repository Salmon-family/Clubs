package com.thechance.viewmodels.conversation

import androidx.lifecycle.SavedStateHandle

class ConversationArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[ID_ARG])

    companion object {
        const val ID_ARG = "id"
    }
}