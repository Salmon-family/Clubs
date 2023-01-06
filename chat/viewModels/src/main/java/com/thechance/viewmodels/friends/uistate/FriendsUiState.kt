package com.thechance.viewmodels.friends.uistate

data class FriendsUiState(
    val friends: List<FriendUiState> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isFail: Boolean = false,
)

fun FriendsUiState.isEmpty() = friends.isEmpty()