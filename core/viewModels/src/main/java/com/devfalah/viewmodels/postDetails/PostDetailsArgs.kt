package com.devfalah.viewmodels.postDetails

import androidx.lifecycle.SavedStateHandle

class PostDetailsArgs(savedStateHandle: SavedStateHandle) {

    val postId: Int = checkNotNull(savedStateHandle[POST_ID]).toString().toInt()
    val publisherId: Int = checkNotNull(savedStateHandle[PUBLISHER_ID]).toString().toInt()

    companion object {
        const val POST_ID = "pos_id"
        const val PUBLISHER_ID = "publisher_id"
    }
}