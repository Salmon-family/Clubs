package com.devfalah.viewmodels.postDetails

import androidx.lifecycle.SavedStateHandle
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class PostDetailsArgs(savedStateHandle: SavedStateHandle) {

    val postId: Int = checkNotNull(savedStateHandle[POST_ID]).toString().toInt()
    val publisherId: Int = checkNotNull(savedStateHandle[PUBLISHER_ID]).toString().toInt()
//    val publisherName: String = checkNotNull(savedStateHandle[PUBLISHER_NAME]).toString()
//    val publisherImageUrl: String = URLDecoder.decode(
//        checkNotNull(savedStateHandle[PUBLISHER_IMAGE_URL]).toString(),
//        StandardCharsets.UTF_8.toString()
//    )


    companion object {
        const val POST_ID = "POST_ID"
        const val PUBLISHER_ID = "PUBLISHER_ID"
    }
}