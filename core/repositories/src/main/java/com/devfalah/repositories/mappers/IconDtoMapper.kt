package com.devfalah.repositories.mappers

import com.devfalah.entities.Icon
import com.devfalah.repositories.models.IconDto

fun IconDto.toEntity(): Icon {
    return Icon(
        small = this.small ?: "",
        smaller = this.smaller ?: "",
        large = this.large ?: "",
        larger = this.larger ?: "",
        topBar = this.topbar ?: "",
    )
}