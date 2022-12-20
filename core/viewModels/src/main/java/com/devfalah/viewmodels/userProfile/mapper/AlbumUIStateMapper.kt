package com.devfalah.viewmodels.userProfile.mapper

import com.devfalah.entities.Album
import com.devfalah.viewmodels.userProfile.AlbumUIState

fun List<Album>.toUIState() = map { it.toUIState() }

fun Album.toUIState(): AlbumUIState {
    return AlbumUIState(
        albumID = albumID,
        albumCover = albumCoverPicture
    )
}


