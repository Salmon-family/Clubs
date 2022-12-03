package com.devfalah.repositories.mappers

import com.devfalah.entities.Post
import com.devfalah.repositories.ConvertDate
import com.devfalah.repositories.models.WallPostDTO

fun WallPostDTO.toEntity(): Post {
    return Post(
        postID = post?.guid ?: 0,
        privacy = post?.access == "3",
        createdTime = post?.timeCreated?.let { ConvertDate().convertTime(it) } ?: "",
        content = text ?: "",
        image = image ?: "",
        totalLikes = post?.totalLikes ?: 0,
        totalComments = post?.totalComments ?: 0,
        posterName = postedUser?.firstName ?: "",
        posterID = postedUser?.guid ?: 0,
        posterImage = postedUser?.icon?.large?.substringBefore("?") ?: "",
        isLikedByUser = post?.isLikedByUser ?: false
    )
}