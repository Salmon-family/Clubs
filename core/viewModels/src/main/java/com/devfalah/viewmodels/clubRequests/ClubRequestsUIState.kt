package com.devfalah.viewmodels.clubRequests

import com.devfalah.viewmodels.friendRequest.UserState

data class ClubRequestsUIState(
    val users: List<UserState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val minorError: String = "",
)