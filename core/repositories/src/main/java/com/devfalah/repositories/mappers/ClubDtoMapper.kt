package com.devfalah.repositories.mappers

import com.devfalah.entities.Club
import com.devfalah.entities.GroupWall
import com.devfalah.entities.GroupWallPost
import com.devfalah.entities.Post
import com.devfalah.repositories.models.PostDTO
import com.devfalah.repositories.models.group.GroupDTO
import com.devfalah.repositories.models.group.GroupPostDto
import com.devfalah.repositories.models.group.GroupWallDto

fun List<GroupDTO>.toEntity(): List<Club> = map { it.toEntity() }

fun GroupDTO.toEntity(): Club {
    return Club(
        id = guid ?: 0,
        ownerId = ownerGuid ?: 0,
        name = title ?: "",
        description = description ?: "",
        privacy = membership ?: "1",
        isMember = ismember ?: false,
        requestExists = requestExists ?: false,
        isOwner = false
    )
}

fun GroupWallDto.toEntity(): GroupWall {
    return GroupWall(
        count = count ?: 0,
        post = posts?.toGroupWallPostEntity() ?: emptyList(),
        offset = offset ?: "1"
    )
}

fun List<GroupPostDto>.toGroupWallPostEntity(): List<GroupWallPost> =
    map { it.toEntity() }

fun GroupPostDto.toEntity(): GroupWallPost {
    return GroupWallPost(
        friends = friends ?: emptyList(),
        location = location ?: "",
        post = post.toEntity().copy(
            publisherId = user.guid ?: 0,
            publisher = user.fullName ?: ""
        ),
        text = text ?: "",
        user = user.toEntity(),
    )
}

fun PostDTO.toEntity(): Post {
    return Post(
        id = guid ?: 0,
        privacy = access == "1",
        createdTime = timeCreated ?: 0L,
        content = description ?: "",
        imageUrl = profileImage?.substringBefore("?") ?: "",
        totalLikes = totalLikes ?: 0,
        totalComments = totalComments ?: 0,
        publisher = "",
        publisherId = ownerGuid ?: 0,
        publisherImageUrl = profileImage ?: "",
        isLiked = isLikedByUser ?: false,
        isSaved = false,
        posterGuid = "",
        groupId = 0,
        groupName = "",
        isMyPost = false
    )
}
