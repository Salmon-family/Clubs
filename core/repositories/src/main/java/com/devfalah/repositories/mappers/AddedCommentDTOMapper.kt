package com.devfalah.repositories.mappers

import com.devfalah.entities.Comment
import com.devfalah.entities.User
import com.devfalah.repositories.models.AddedCommentDTO

internal fun AddedCommentDTO.toEntity(): Comment {
    return Comment(
        id = comment?.id ?: 0,
        timeCreated = comment?.timeCreated ?: 0L,
        content = comment?.commentsPost ?: "",
        totalLikes = comment?.totalLikes ?: 0,
        isLikedByUser = comment?.isLikedByUser ?: false,
        user = user?.toEntity() ?: User(),
        isMyComment = true
    )
}