package com.devfalah.viewmodels.postDetails.mapper

import com.devfalah.entities.Comment
import com.devfalah.entities.PublishTime
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PublishTimeUIState
import com.devfalah.viewmodels.util.ConvertDate

fun Comment.toUIState(): CommentUIState {
    return CommentUIState(
        id = id,
        text = content,
        userPictureUrl = user.profileUrl,
        userName = user.name,
        userId = user.id,
        isOwnerComment = isMyComment,
        timeCreate = ConvertDate().convertTime(timeCreated).toUIState(),
        totalLikes = totalLikes,
        isLikedByUser = isLikedByUser
    )
}

fun PublishTime.toUIState(): PublishTimeUIState {
    return PublishTimeUIState(
        value = value,
        description = description
    )
}

fun List<Comment>.toUIState() = map { it.toUIState() }