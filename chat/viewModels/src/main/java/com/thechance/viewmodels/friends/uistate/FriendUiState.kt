package com.thechance.viewmodels.friends.uistate

import com.thechance.entities.Friend

data class FriendUiState(
    val id: Int,
    val icon: String,
    val name: String,
    val title: String,
)


fun Friend.toUiState(): FriendUiState {
    return FriendUiState(
        id,
        icon,
        name,
        title
    )
}