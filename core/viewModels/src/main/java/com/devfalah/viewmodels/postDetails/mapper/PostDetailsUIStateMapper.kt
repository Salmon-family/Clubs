package com.devfalah.viewmodels.postDetails.mapper

import com.devfalah.entities.Comment
import com.devfalah.viewmodels.postDetails.CommentUIState

fun Comment.toUIState(mainUserId: Int): CommentUIState {
    return CommentUIState(
        id = id,
        text = content,
        userPictureUrl = user.profileUrl,
        userName = user.name,
        userId = user.id,
        isOwnerComment = mainUserId == user.id
    )
}


fun List<Comment>.toUIState(mainUserId: Int) = map { it.toUIState(mainUserId) }