package com.devfalah.viewmodels.postDetails

import com.devfalah.viewmodels.userProfile.PostUIState

data class PostDetailsUIState(
    val id: Int = 0,
    val post: PostUIState = PostUIState(),
    val comments: List<CommentUIState> = emptyList(),

    val commentText: String = "",

    val isLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val error: String = ""

)

data class CommentUIState(
    val id: Int = 0,
    val text: String = "",
    val userId: Int = 0,
    val userName: String = "",
    val userPictureUrl: String = "",
    val isOwnerComment: Boolean = false,
)


