package com.devfalah.viewmodels.postDetails

import com.devfalah.viewmodels.userProfile.PostUIState

data class PostDetailsUIState(
    val userId: Int = 0,
    val postDetails: PostUIState = PostUIState(),
    val comments: List<CommentUIState> = emptyList(),
    val comment: String = "",
    val loading: Boolean = false,
    val error: String = "",
)

data class CommentUIState(
    val id: Int = 0,
    val postId: Int = 0,

    // edit comment
    var isEdited: Boolean = false,
    val commentEdited: String = "",

    // author and add of comment
    val ownerCommentId: Int = 0,
    val imageFile: String = "",

    // user details
    val userName: String = "",
    val userImage: String = "",

    // comment details
    val isLikedByUser: Boolean = false,
    val totalLikes: Int = 0,
    val content: String = "",
    val time: String = "",
    val type: String = "",

    //delete comment
    val isDeleted: Boolean = false,

    // comment directions
    val isCommentDirection: Boolean = false,
    val isMyComment: Boolean = false,
    val isOwner: Boolean = false,
)
