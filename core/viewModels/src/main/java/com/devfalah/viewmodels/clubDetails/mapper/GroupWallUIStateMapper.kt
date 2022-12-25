package com.devfalah.viewmodels.clubDetails.mapper

import com.devfalah.entities.GroupWallPost
import com.devfalah.viewmodels.util.ConvertDate
import com.devfalah.viewmodels.userProfile.PostUIState

fun List<GroupWallPost>.toUIState(groupId: Int, groupName: String): List<PostUIState> =
    map { it.toUIState(groupId, groupName) }


fun GroupWallPost.toUIState(groupId: Int, groupName: String): PostUIState {
    return PostUIState(
        postId = post.id,
        publisherName = user.name,
        publisherImage = user.profileUrl,
        publisherId = user.id,
        privacy = post.privacy,
        createdData = ConvertDate().convertTime(post.createdTime),
        createdDataValue = post.createdTime,
        totalLikes = post.totalLikes,
        totalComments = post.totalComments,
        isSaved = post.isSaved,
        isLikedByUser = post.isLiked,
        postImage = post.imageUrl,
        postContent = text,
        groupId = groupId,
        groupName = groupName
    )
}