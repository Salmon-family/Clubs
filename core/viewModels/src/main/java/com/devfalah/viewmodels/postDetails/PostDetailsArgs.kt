package com.devfalah.viewmodels.postDetails

import androidx.lifecycle.SavedStateHandle


class PostDetailsArgs(savedStateHandle: SavedStateHandle) {
    val userId: Int = checkNotNull(savedStateHandle[USER_ID])
    val postId: Int = checkNotNull(savedStateHandle[POST_ID])

    companion object {
        const val USER_ID = "userId"
        const val POST_ID = "postId"
    }
}