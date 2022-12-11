package com.devfalah.viewmodels.userProfile.mapper

import com.devfalah.entities.Post
import com.devfalah.viewmodels.userProfile.PostUIState

fun List<Post>.toUIState() = map { it.toUIState() }

fun Post.toUIState(): PostUIState {
    return PostUIState(
        postId = id,
        publisherId = publisherId,
        publisherImage = publisherImageUrl,
        publisherName = publisher,
        privacy = privacy,
        createdData = createdTimeValue,
        totalLikes = totalLikes,
        totalComments = totalComments,
        isSaved = false,
        isLikedByUser = isLiked,
        postImage = imageUrl,
        postContent = content,
    )
}


fun PostUIState.toEntity(): Post {
    return Post(
        id = postId,
        publisherId = publisherId,
        publisherImageUrl = publisherImage,
        publisher = publisherName,
        privacy = privacy,
        createdTimeValue = createdData,
        totalLikes = totalLikes,
        totalComments = totalComments,
        isLiked = isLikedByUser,
        imageUrl = postImage,
        content = postContent,
        createdTime = 0L
    )
}


