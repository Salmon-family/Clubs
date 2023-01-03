package com.thechance.viewmodels.friends.uistate

data class FriendsUiState(
    val friends: List<FriendUiState> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isFail: Boolean = false,
    val friendsCount: Int = 0,
    val isLoadingMore: Boolean = false,
    val isLastPage: Boolean = false,
)

fun FriendsUiState.isEmpty() = friends.isEmpty()