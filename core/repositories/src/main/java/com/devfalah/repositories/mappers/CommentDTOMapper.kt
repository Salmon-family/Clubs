package com.devfalah.repositories.mappers

import com.devfalah.entities.Comment
import com.devfalah.entities.User
import com.devfalah.repositories.models.CommentDto

internal fun CommentDto.toEntity(): Comment {
    return Comment(
        id = id ?: 0,
        timeCreated = timeCreated ?: 0L,
        content = commentsPost ?: "",
        totalLikes = totalLikes ?: 0,
        isLikedByUser = isLikedByUser ?: false,
        user = user?.toEntity()?: User(),
        isMyComment = true
    )
}


internal fun List<CommentDto>.toEntity() = map { it.toEntity() }


