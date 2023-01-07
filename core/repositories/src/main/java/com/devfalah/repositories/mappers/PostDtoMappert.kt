package com.devfalah.repositories.mappers

import com.devfalah.entities.Post
import com.devfalah.repositories.models.post.WallPostDTO

internal fun WallPostDTO.toEntity(groupId: Int = 0): Post? {
    return if (text == "null:data") {
        Post(
            id = post?.guid ?: 0,
            privacy = post?.access == "3",
            createdTime = post?.timeCreated ?: 0L,
            content = "",
            imageUrl = post?.profileImage?.substringBefore("?") ?: "",
            totalLikes = post?.totalLikes ?: 0,
            totalComments = post?.totalComments ?: 0,
            publisher = postedUser?.fullName ?: "",
            publisherId = postedUser?.guid ?: 0,
            publisherImageUrl = postedUser?.icon?.large?.substringBefore("?") ?: "",
            isLiked = post?.isLikedByUser ?: false,
            isSaved = false,
            posterGuid = "",
            groupId = group?.guid ?: groupId,
            groupName = group?.title ?: "",
            isMyPost = false,
            isFromAlbum = true
        )
    } else {
        Post(
            id = post?.guid ?: 0,
            privacy = post?.access == "3",
            createdTime = post?.timeCreated ?: 0L,
            content = text?.let { if (it != "false") { it } else { "" } } ?: "",
            imageUrl = image?.substringBefore("?") ?: "",
            totalLikes = post?.totalLikes ?: 0,
            totalComments = post?.totalComments ?: 0,
            publisher = postedUser?.fullName ?: "",
            publisherId = postedUser?.guid ?: 0,
            publisherImageUrl = postedUser?.icon?.large?.substringBefore("?") ?: "",
            isLiked = post?.isLikedByUser ?: false,
            isSaved = false,
            posterGuid = "",
            groupId = group?.guid ?: groupId,
            groupName = group?.title ?: "",
            isMyPost = false,
            isFromAlbum = false
        )
    }
}

internal fun List<WallPostDTO>.toEntity(groupID: Int = 0): List<Post> =
    mapNotNull { it.toEntity(groupID) }
