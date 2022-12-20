package com.devfalah.viewmodels.allSearchResult

import com.devfalah.viewmodels.friends.FriendUIState
import com.devfalah.viewmodels.search.ClubUIState

data class AllSearchResultUIState(
    val isLoading: Boolean = false,
    val error: String = "",
    val title: String = "",
    val clubs: List<ClubUIState> = emptyList(),
    val users: List<FriendUIState> = emptyList(),
    val isClub: Boolean = false
)