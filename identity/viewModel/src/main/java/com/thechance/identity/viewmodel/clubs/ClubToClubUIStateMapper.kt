package com.thechance.identity.viewmodel.clubs

import com.thechance.identity.entities.Club

fun Club.toUIState(): ClubUIState{
    return ClubUIState(
        id = this.id,
        name = this.name,
        icon = this.icon
    )
}

fun List<Club>.toUIState(): List<ClubUIState> = map { it.toUIState() }