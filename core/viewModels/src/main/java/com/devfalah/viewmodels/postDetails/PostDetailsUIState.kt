package com.devfalah.viewmodels.postDetails

import com.devfalah.viewmodels.userProfile.PostUIState

data class PostDetailsUIState(
    val id: Int = 0,
    val post: PostUIState = PostUIState(),
    val comments: List<CommentUIState> = emptyList(),
    val commentText: String = "",
    val isFromClub: Boolean = false,
    val isLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val isPostDeleted: Boolean = false,
    val error: String = "",
    val minorError: String = ""
)

data class CommentUIState(
    val id: Int = 0,
    val text: String = "",
    val userId: Int = 0,
    val userName: String = "",
    val userPictureUrl: String = "",
    val timeCreate: String = "",
    val totalLikes: Int = 0,
    val isOwnerComment: Boolean = false,
    val isLikedByUser: Boolean = false,
)


