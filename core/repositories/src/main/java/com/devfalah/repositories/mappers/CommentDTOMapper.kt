package com.devfalah.repositories.mappers

import com.devfalah.entities.Comment
import com.devfalah.repositories.models.CommentDto

fun CommentDto.toEntity(): Comment {
    return Comment(
        id = id ?: 0,
        timeCreated = timeCreated ?: 0L,
        content = commentsPost ?: "",
        totalLikes = totalLikes ?: 0,
        isLikedByUser = isLikedByUser ?: false,
        user = user.toEntity()
    )
}


fun List<CommentDto>.toEntity() = map { it.toEntity() }