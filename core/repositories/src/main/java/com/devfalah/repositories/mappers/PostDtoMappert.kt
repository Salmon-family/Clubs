package com.devfalah.repositories.mappers

import com.devfalah.entities.Post
import com.devfalah.repositories.ConvertDate
import com.devfalah.repositories.models.WallPostDTO

fun WallPostDTO.toEntity(): Post? {
    return if ( text == "null:data"){
        null
    }else{
        Post(
            id = post?.guid ?: 0,
            privacy = post?.access == "3",
            createdTime= post?.timeCreated?:0L,
            content = text?.let {
                if (it != "false") { it } else { "" } } ?: "",
            imageUrl =  image?.substringBefore("?")?:"" ,
            totalLikes = post?.totalLikes ?: 0,
            totalComments = post?.totalComments ?: 0,
            publisher = postedUser?.fullName ?: "",
            publisherId = postedUser?.guid ?: 0,
            publisherImageUrl = postedUser?.icon?.large?.substringBefore("?") ?: "",
            isLiked = post?.isLikedByUser ?: false,
            isSaved = false,
            posterGuid = ""
        )
    }
}

fun List<WallPostDTO>.toEntity():List<Post> = mapNotNull { it.toEntity() }