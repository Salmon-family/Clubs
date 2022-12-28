package com.devfalah.viewmodels.search

import com.devfalah.entities.SearchResult
import com.devfalah.viewmodels.friends.FriendUIState
import com.devfalah.viewmodels.friends.toUIState

data class SearchUIState(
    val keyword: String = "",
    val clubs: List<ClubUIState> = emptyList(),
    val users: List<FriendUIState> = emptyList(),
    val showSeeAllClubs: Boolean = true,
    val showSeeAllUsers: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = ""
)

data class ClubUIState(
    val id: Int = 0,
    val title: String = "",
    val description: String = ""
)

fun SearchResult.toUIState(): SearchUIState {
    return SearchUIState(
        clubs = clubs.toUIState(),
        users = users.toUIState(),
        showSeeAllClubs = isMoreClubs,
        showSeeAllUsers = isMoreUsers
    )
}