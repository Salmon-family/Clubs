package com.devfalah.viewmodels.postDetails

import com.devfalah.viewmodels.userProfile.PostUIState

data class PostDetailsUIState(
    val postDetails: PostUIState = PostUIState(),
    val comments: List<CommentUIState> = emptyList(),
    val comment: String = "",
    val isMyProfile: Boolean = false,
    val loading: Boolean = false,
    val error: String = "",
)

data class CommentUIState(
    val id: Int,
    val userName: String,
    val userImage: String,
    val isLikedByUser: Boolean,
    val totalLikes: Int,
    val isDeleted: Boolean,
    val content: String,
    val ownerCommentId: Int,
    val postId: Int,
    val time: String,
    val type: String,
)
