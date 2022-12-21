package com.devfalah.viewmodels.userProfile.mapper

import com.devfalah.entities.Post
import com.devfalah.viewmodels.ConvertDate
import com.devfalah.viewmodels.userProfile.PostUIState

fun List<Post>.toUIState() = map { it.toUIState() }

fun Post.toUIState(): PostUIState {
    return PostUIState(
        postId = id,
        publisherId = publisherId,
        publisherImage = publisherImageUrl,
        publisherName = publisher,
        privacy = privacy,
        createdData = ConvertDate().convertTime(createdTime),
        totalLikes = totalLikes,
        totalComments = totalComments,
        isSaved = isSaved,
        isLikedByUser = isLiked,
        postImage = imageUrl,
        postContent = content,
        createdDataValue = createdTime
    )
}


fun PostUIState.toEntity(): Post {
    return Post(
        id = postId,
        publisherId = publisherId,
        publisherImageUrl = publisherImage,
        publisher = publisherName,
        privacy = privacy,
        totalLikes = totalLikes,
        totalComments = totalComments,
        isLiked = isLikedByUser,
        imageUrl = postImage,
        content = postContent,
        createdTime = createdDataValue,
        isSaved = isSaved,
        posterGuid = ""
    )
}


