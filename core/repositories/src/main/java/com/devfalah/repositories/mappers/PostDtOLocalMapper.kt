package com.devfalah.repositories.mappers

import com.devfalah.entities.Post
import com.devfalah.repositories.models.post.PostLocalDto

internal fun Post.toEntity(): PostLocalDto {
    return PostLocalDto(
        id = id,
        privacy = privacy,
        createdTime = createdTime,
        content = content,
        imageUrl = imageUrl,
        totalLikes = totalLikes,
        totalComments = totalComments,
        publisher = publisher,
        publisherId = publisherId,
        publisherImageUrl = publisherImageUrl,
        isLiked = isLiked,
        groupId = groupId,
        groupName = groupName
    )
}

internal fun PostLocalDto.toEntity(): Post {
    return Post(
        id = id,
        privacy = privacy,
        createdTime = createdTime,
        content = content,
        imageUrl = imageUrl,
        totalLikes = totalLikes,
        totalComments = totalComments,
        publisher = publisher,
        publisherId = publisherId,
        publisherImageUrl = publisherImageUrl,
        isLiked = isLiked,
        posterGuid = "",
        isSaved = false,
        groupId = groupId,
        groupName = groupName,
        isMyPost = false
    )
}

internal fun List<PostLocalDto>.toEntity() = map { it.toEntity() }