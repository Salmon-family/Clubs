package com.thechance.identity.viewmodel.clubs

import com.thechance.identity.viewmodel.utils.ErrorMessageType

data class ClubsUIState(
    val clubs: List<ClubUIState> = emptyList(),
    val selectedClubs: List<ClubUIState> = emptyList(),
    val errorType: ErrorMessageType = ErrorMessageType.NO_ERROR,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
)
