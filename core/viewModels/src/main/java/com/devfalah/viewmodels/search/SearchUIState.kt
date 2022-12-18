package com.devfalah.viewmodels.search

import com.devfalah.viewmodels.friends.FriendUIState

data class SearchUIState(
    val userId: Int = 0,
    val keyword: String = "",
    val clubs: List<ClubUIState> = emptyList(),
    val users: List<FriendUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class ClubUIState(
    val id: Int = 0,
    val title: String = "",
    val description: String = ""
)
