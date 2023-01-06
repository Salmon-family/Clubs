package com.devfalah.repositories.mappers

import com.devfalah.entities.Post
import com.devfalah.repositories.models.post.PostHome
import com.devfalah.repositories.models.post.PostHomeDto


internal fun List<PostHomeDto>.toEntity() = map { it.toEntity() }

internal fun PostHomeDto.toEntity(): Post {
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
        groupId = 0,
        groupName = "",
        isMyPost = false
    )
}

internal fun PostHome.toPostHomeEntity(): Post {
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
        groupId = 0,
        groupName = "",
        isMyPost = false,
        isSaved = saved
    )
}

internal fun List<PostHome>.toPostHomeEntity() = map { it.toPostHomeEntity() }

internal fun List<Post>.toHomeEntity() = map { it.toHomeEntity() }

internal fun Post.toHomeEntity(): PostHomeDto {
    return PostHomeDto(
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
    )
}
