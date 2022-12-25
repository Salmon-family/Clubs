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
        requestExists = requestExists ?: false
    )
}

fun GroupWallDto.toEntity(groupId: Int): GroupWall {
    return GroupWall(
        count = count ?: 0,
        post = posts?.toGroupWallPostEntity(groupId) ?: emptyList(),
        offset = offset ?: "1"
    )
}

fun List<GroupPostDto>.toGroupWallPostEntity(groupId: Int): List<GroupWallPost> =
    map { it.toEntity(groupId) }

fun GroupPostDto.toEntity(groupId: Int): GroupWallPost {
    return GroupWallPost(
        friends = friends ?: emptyList(),
        location = location ?: "",
        post = post.toEntity(groupId = groupId),
        text = text ?: "",
        user = user.toEntity(),
    )

}

fun PostDTO.toEntity(groupId: Int): Post {
    return Post(
        id = guid ?: 0,
        privacy = access == "1",
        createdTime = timeCreated ?: 0L,
        posterGuid = posterGuid.toString(),
        totalLikes = totalLikes ?: 0,
        totalComments = totalComments ?: 0,
        isLiked = isLikedByUser ?: false,
        isSaved = false,
        content = description ?: "",
        imageUrl = profileImage ?: "",
        publisherId = 0,
        publisher = "",
        publisherImageUrl = profileImage ?: "",
        groupId = groupId,
        groupName = ""
    )

}
