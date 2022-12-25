package com.devfalah.viewmodels.postCreation

import androidx.lifecycle.SavedStateHandle

class PostCreationArgs(savedStateHandle: SavedStateHandle) {

    val clubId: Int = checkNotNull(savedStateHandle[GROUP_ID]).toString().toInt()

    companion object {
        const val GROUP_ID = "GROUP_ID"
    }
}