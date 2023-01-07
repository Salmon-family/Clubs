package com.thechance.identity.viewmodel.clubs

import com.thechance.identity.entities.Club

fun ClubUIState.toEntity(): Club{
    return Club(
        id = this.id,
        name = this.name,
        outLineIcon = this.outLineIcon,
        fillLineIcon = this.fillLineIcon
    )
}

fun List<ClubUIState>.toEntity(): List<Club> = map { it.toEntity() }