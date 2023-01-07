package com.devfalah.viewmodels.clubDetails.mapper

import com.devfalah.entities.Club
import com.devfalah.viewmodels.clubDetails.DetailsUIState

fun Club.toClubDetailsUIState(): DetailsUIState{
    return DetailsUIState(
        clubId = this.id,
        ownerId = this.ownerId,
        name = this.name,
        description = this.description,
        isOwner = this.isOwner,
        isClubPublic = this.isClubPublic,
    )
}