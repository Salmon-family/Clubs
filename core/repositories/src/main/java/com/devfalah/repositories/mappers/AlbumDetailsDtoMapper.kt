package com.devfalah.repositories.mappers

import com.devfalah.entities.Album
import com.devfalah.repositories.models.album.AlbumDTO

fun AlbumDTO?.toEntity(): Album {
    return Album(
        albumID = this?.album?.guid ?: 0,
        ownerID = this?.album?.ownerGuid ?: 0,
        albumTitle = this?.album?.title ?: "",
        albumCoverPicture = this?.imageUrl?.substringBefore("?") ?: ""
    )
}