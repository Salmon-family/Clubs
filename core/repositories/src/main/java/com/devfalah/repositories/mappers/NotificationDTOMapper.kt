package com.devfalah.repositories.mappers

import com.devfalah.entities.*
import com.devfalah.repositories.models.FriendDTO
import com.devfalah.repositories.models.notification.NotificationDTO
import com.devfalah.repositories.models.notification.NotificationsDTO
import com.devfalah.repositories.models.notification.PosterDTO

fun NotificationsDTO.toEntity(): Notifications{
    return Notifications(
        entity = this.entity ?: false,
        notification = this.notification?.toEntity() ?: Notification(),
        post = this.post ?: false,
        poster = this.poster?.toEntity() ?: Poster()

    )
}

fun NotificationDTO.toEntity(): Notification{
    return Notification(
        guid = this.guid ?: 0,
        itemGuid = this.itemGuid ?:0,
        ownerGuid = this.ownerGuid ?:0,
        posterGuid = this.posterGuid ?:0,
        subjectGuid = this.subjectGuid?:0,
        timeCreated = this.timeCreated ?:0,
        type = this.type ?:"",
        viewed = this.viewed ?:""
    )
}

fun PosterDTO.toEntity(): Poster {
    return Poster(
        fullName = this.fullname ?:"",
        guid = this.guid ?:0,
        icon = this.icon ?:""
    )

}
