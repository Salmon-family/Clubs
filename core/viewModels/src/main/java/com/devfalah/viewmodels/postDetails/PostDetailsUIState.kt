package com.devfalah.viewmodels.postDetails

import com.devfalah.viewmodels.userProfile.PostUIState

data class PostDetailsUIState(
    val userId: Int = 0,
    val postDetails: PostUIState = PostUIState(),
    val comments: List<CommentUIState> = emptyList(),
    val comment: String = "",
    val isMyProfile: Boolean = false,
    val loading: Boolean = false,
    val error: String = "",
)

data class CommentUIState(
    val id: Int = 0,
    val postId: Int = 0,
    val commentEdited: String = "",
    val ownerCommentId: Int = 0,
    val isEdited: Boolean = false,
    val userName: String = "",
    val userImage: String = "",
    val isLikedByUser: Boolean = false,
    val totalLikes: Int = 0,
    val isDeleted: Boolean = false,
    val content: String = "",
    val time: String = "",
    val type: String = "",
)
