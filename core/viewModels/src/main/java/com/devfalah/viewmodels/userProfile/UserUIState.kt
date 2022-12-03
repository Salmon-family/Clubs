package com.devfalah.viewmodels.userProfile

import com.devfalah.entities.Album
import com.devfalah.entities.User

data class UserUIState(
    val userDetails: UserDetailsUIState = UserDetailsUIState(),
    val albums: List<AlbumUIState> = emptyList(),
    val friends: List<UserDetailsUIState> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)

data class UserDetailsUIState(
    val userID: Int = 0,
    val name: String = "",
    val userName: String = "",
    val title: String = "",
    val description: String = "",
    val coverUrl: String = "",
    val profilePicture: String = ""
)

data class AlbumUIState(
    val albumID: Int = 0,
    val albumCover: String
)

////// Mappers
fun User.toUIState(): UserDetailsUIState {
    return UserDetailsUIState(
        userID = userID,
        name = name,
        userName = username,
        title = title,
        description = description,
        coverUrl = coverUrl,
        profilePicture = icon.large
    )
}

fun Album.toUIState(): AlbumUIState {
    return AlbumUIState(
        albumID = albumID,
        albumCover = albumCoverPicture
    )
}