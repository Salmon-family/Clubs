package com.devfalah.repositories.mappers

import com.devfalah.entities.Album
import com.devfalah.entities.Notification
import com.devfalah.repositories.models.album.AlbumDTO
import com.devfalah.repositories.models.notification.NotificationsDTO

fun AlbumDTO?.toEntity(): Album {
    return Album(
        albumID = this?.album?.guid ?: 0,
        ownerID = this?.album?.ownerGuid ?: 0,
        albumTitle = this?.album?.title ?: "",
        albumCoverPicture = this?.imageUrl?.substringBefore("?") ?: ""
    )
}

fun List<AlbumDTO>.toEntity():List<Album> = map { it.toEntity() }
