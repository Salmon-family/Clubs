package com.devfalah.viewmodels.postDetails

import com.devfalah.viewmodels.userProfile.PostUIState

data class PostDetailsUIState(
    val userId: Int = 0,
    val postDetails: PostUIState = PostUIState(),
    val comments: List<CommentUIState> = emptyList(),
    val comment: String = "",
    val isLoading: Boolean = false,
    val isPagerLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val majorError: String = "",
    val minorError: String = "",
    val pagerError: String = "",
)

data class CommentUIState(
    val id: Int = 0,
    val postId: Int = 0,
    var isEdited: Boolean = false,
    val ownerCommentId: Int = 0,
    val imageFile: String = "",
    val userName: String = "",
    val userImage: String = "",
    val isLikedByUser: Boolean = false,
    val totalLikes: Int = 0,
    val content: String = "",
    val time: String = "",
    val type: String = "",
    val isDeleted: Boolean = false,
    val isOwnerComment: Boolean = false,
)
