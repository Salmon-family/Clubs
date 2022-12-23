package com.devfalah.viewmodels.postDetails

import androidx.lifecycle.SavedStateHandle

class PostDetailsArgs(savedStateHandle: SavedStateHandle) {

    val postId: Int = checkNotNull(savedStateHandle[POST_ID]).toString().toInt()
    val isSaved: Boolean = checkNotNull(savedStateHandle[POST_SAVED]).toString().toBoolean()

    companion object {
        const val POST_ID = "POST_ID"
        const val POST_SAVED = "POST_SAVED"
    }
}