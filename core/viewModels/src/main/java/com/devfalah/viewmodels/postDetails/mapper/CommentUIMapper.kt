package com.devfalah.viewmodels.postDetails.mapper

import com.devfalah.entities.Comment
import com.devfalah.viewmodels.postDetails.CommentUIState

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
        time = time,
        type = type,
    )
}

fun List<Comment>.toUIState() = map { it.toUIState() }
