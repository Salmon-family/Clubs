package com.devfalah.repositories.mappers

import com.devfalah.entities.Notification
import com.devfalah.entities.Notifications
import com.devfalah.repositories.ConvertDate
import com.devfalah.repositories.models.notification.NotificationDTO
import com.devfalah.repositories.models.notification.NotificationsDTO
import com.devfalah.repositories.models.notification.PosterDTO

fun NotificationsDTO.toEntity(): Notifications {
    return Notifications(
        entity = this.entity ?: false,
        notification = this.notification?.toEntity(this.poster) ?: Notification(),
        post = this.post ?: false,
    )
}

fun NotificationDTO.toEntity(poster: PosterDTO?): Notification {
    return Notification(
        guid = this.guid ?: 0,
        itemGuid = this.itemGuid ?: 0,
        ownerGuid = this.ownerGuid ?: 0,
        subjectGuid = this.subjectGuid ?: 0,
        timeCreated = this.timeCreated?.let { ConvertDate().convertTime(it) } ?: "",
        type = this.type ?: "",
        viewed = this.viewed != null,
        posterID = this.posterGuid ?: 0,
        posterName = poster?.fullname ?: "",
        posterImage = poster?.icon ?: ""
    )
}

