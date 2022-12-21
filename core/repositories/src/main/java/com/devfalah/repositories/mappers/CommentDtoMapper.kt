package com.devfalah.repositories.mappers

import com.devfalah.entities.Comment
import com.devfalah.entities.Success
import com.devfalah.repositories.ConvertDate
import com.devfalah.repositories.models.CommentDto
import com.devfalah.repositories.models.SuccessDTO

fun CommentDto.toEntity(userId: Int): Comment {
    return Comment(
        id = id ?: 0,
        content = commentsPost ?: "",
        userName = user?.fullName ?: "",
        userImage = user?.icon?.large ?: "",
        isOwnerComment = ownerGuid == userId,
        isLikedByUser = isLikedByUser ?: false,
        totalLikes = totalLikes ?: 0,
        isDeleted = false,
        ownerCommentId = ownerGuid ?: 0,
        postId = subjectGuid ?: 0,
        time = timeCreated?.let { ConvertDate().convertTime(it) } ?: "",
        type = type ?: "",
    )
}

fun SuccessDTO.toEntity() = Success(
    success = success ?: ""
)

fun List<CommentDto>.toEntity(userId: Int): List<Comment> = mapNotNull { it.toEntity(userId) }
