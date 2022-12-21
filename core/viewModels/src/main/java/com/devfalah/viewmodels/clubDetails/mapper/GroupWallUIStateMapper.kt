package com.devfalah.viewmodels.clubDetails.mapper

import com.devfalah.entities.GroupWallPost
import com.devfalah.viewmodels.ConvertDate
import com.devfalah.viewmodels.userProfile.PostUIState

fun List<GroupWallPost>.toUIState(): List<PostUIState> = map { it.toUIState() }


fun GroupWallPost.toUIState(): PostUIState {
    return PostUIState(
        postId = post.id,
        publisherName = post.publisher,
        publisherImage = post.publisherImageUrl,
        publisherId = post.publisherId,
        privacy = post.privacy,
        createdData = ConvertDate().convertTime(post.createdTime),
        createdDataValue = post.createdTime,
        totalLikes = post.totalLikes,
        totalComments = post.totalComments,
        isSaved = post.isSaved,
        isLikedByUser = post.isLiked,
        postImage = post.imageUrl,
        postContent = post.content,
    )
}