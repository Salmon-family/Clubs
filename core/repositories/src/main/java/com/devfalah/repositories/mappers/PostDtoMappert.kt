package com.devfalah.repositories.mappers

import com.devfalah.entities.Post
import com.devfalah.repositories.models.post.WallPostDTO

internal fun WallPostDTO.toEntity(groupId: Int = 0): Post {
    return Post(
        id = post?.guid ?: 0,
        privacy = post?.access == "3",
        createdTime = post?.timeCreated ?: 0L,
        content = if (text == "null:data") { "" }
        else { text?.let { if (it != "false") { it } else { "" } } ?: "" },
        imageUrl = if (text == "null:data") { post?.profileImage?.substringBefore("?") ?: "" }
        else { image?.substringBefore("?") ?: "" },
        totalLikes = post?.totalLikes ?: 0,
        totalComments = post?.totalComments ?: 0,
        publisher = postedUser?.fullName ?: "",
        publisherId = post?.posterGuid?.toInt() ?: 0,
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

internal fun List<WallPostDTO>.toEntity(groupID: Int = 0): List<Post> =
    mapNotNull { it.toEntity(groupID) }
