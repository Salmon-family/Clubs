package com.devfalah.viewmodels.postDetails

import com.devfalah.entities.Comment

fun Comment.toUIState(): CommentUIState {
    return CommentUIState(
        id = id,
        content = content,
        userName = userName,
        userImage = userImage,
        isDeleted = isDeleted,
        isLikedByUser = isLikedByUser,
        totalLikes = totalLikes,
        postId = postId,
        ownerCommentId = ownerCommentId,
        isOwnerComment = isOwnerComment,
        time = time,
        type = type
    )
}

fun List<Comment>.toUIState() = map { it.toUIState() }
