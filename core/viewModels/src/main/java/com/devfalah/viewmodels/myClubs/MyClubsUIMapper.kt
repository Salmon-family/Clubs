package com.devfalah.viewmodels.myClubs

import com.devfalah.entities.ClubSpecial

fun ClubSpecial.toUIState(): SpecialClubsUIState {
    return SpecialClubsUIState(
        id = id,
        name = name,
        image = fillLineIcon
    )
}

fun List<ClubSpecial>.toUIState() = map { it.toUIState() }
