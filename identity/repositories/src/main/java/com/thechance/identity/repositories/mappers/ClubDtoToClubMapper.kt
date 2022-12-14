package com.thechance.identity.repositories.mappers

import com.thechance.identity.entities.Club
import com.thechance.identity.repositories.models.ClubDto

fun ClubDto.toEntity(): Club{
    return Club(
        id = this.guid ?: 0,
        name = this.title ?: "",
        icon = 0
    )
}