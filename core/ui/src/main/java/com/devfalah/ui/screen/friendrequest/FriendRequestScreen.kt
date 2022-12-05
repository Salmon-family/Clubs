package com.devfalah.ui.screen.friendrequest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.screen.friendrequest.friendcomposable.FriendRequests
import com.devfalah.viewmodels.friendRequest.FriendRequestViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FriendRequestScreen(
    navController: NavController,
    friendRequestViewModel: FriendRequestViewModel = hiltViewModel()
) {
    val state by friendRequestViewModel.uiStateFriendRequests.collectAsState()

    FriendRequests(
        friendRequestUiState = state,
        onAcceptButtonClick = friendRequestViewModel::acceptFriendRequest,
        onDeleteButtonClick = friendRequestViewModel::deniedFriendRequest
    )
}
