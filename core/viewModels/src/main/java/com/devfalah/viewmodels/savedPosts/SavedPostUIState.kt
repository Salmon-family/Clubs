package com.devfalah.viewmodels.savedPosts

import com.devfalah.entities.Post
import com.devfalah.viewmodels.postDetails.mapper.toUIState
import com.devfalah.viewmodels.util.ConvertDate
import com.devfalah.viewmodels.userProfile.PostUIState

data class SavedPostUIState(
    val posts: List<PostUIState> = emptyList(),
    val error: String = "",
)

fun List<Post>.toSavedUIState() = map { it.toSavedUIState() }

fun Post.toSavedUIState(): PostUIState {
    return PostUIState(
        postId = id,
        publisherId = publisherId,
        publisherImage = publisherImageUrl,
        publisherName = publisher,
        privacy = privacy,
        createdData = ConvertDate().convertTime(createdTime).toUIState(),
        totalLikes = totalLikes,
        totalComments = totalComments,
        isSaved = true,
        isFromClub = groupName.isNotEmpty(),
        isLikedByUser = isLiked,
        postImage = imageUrl,
        postContent = content,
        createdDataValue = createdTime,
        groupName = groupName,
        groupId = groupId
    )
}