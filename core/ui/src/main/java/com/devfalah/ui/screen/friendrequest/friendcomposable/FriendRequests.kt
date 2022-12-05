package com.devfalah.ui.screen.friendrequest.friendcomposable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devfalah.viewmodels.friendRequest.FriendRequestUiState

@ExperimentalFoundationApi
@Composable
fun FriendRequests(
    friendRequestUiState: FriendRequestUiState,
    onAcceptButtonClick: (Int) -> Unit,
    onDeleteButtonClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = friendRequestUiState.friendRequests,
            key = { currentRequest -> currentRequest.userID }
        ) { userState ->
            FriendRequestItem(
                userState = userState,
                onAcceptButtonClick = onAcceptButtonClick,
                onDeleteButtonClick = onDeleteButtonClick,
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}