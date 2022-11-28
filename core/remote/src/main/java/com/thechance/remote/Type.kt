package com.thechance.remote

enum class LikeType {
    entity,
    post,
    annotation // add like on comment
}

enum class GroupPrivacy(val value: Int) {
    CLOSED(1),
    PUBLIC(2)
}

enum class UserPictureType{
    profile,
    cover
}

enum class PostType{
    user,
    group
}