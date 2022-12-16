package com.devfalah.ui.screen.friendrequest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.friendrequest.friendcomposable.FriendRequestItem
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.friendRequest.FriendRequestUiState
import com.devfalah.viewmodels.friendRequest.FriendRequestViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FriendRequestScreen(
    navController: NavController,
    friendRequestViewModel: FriendRequestViewModel = hiltViewModel()
) {
    val state by friendRequestViewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    FriendRequestsContent(
        friendRequestUiState = state,
        onAcceptButtonClick = friendRequestViewModel::acceptFriendRequest,
        onDeleteButtonClick = friendRequestViewModel::deniedFriendRequest,
        onClickOpenProfile = { navController.navigateToProfile(it) }
    )
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = false
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun FriendRequestsContent(
    friendRequestUiState: FriendRequestUiState,
    onAcceptButtonClick: (Int) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    onClickOpenProfile: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(LightBackgroundColor)
            .fillMaxSize(),
    ) {
        items(
            items = friendRequestUiState.friendRequests,
            key = { currentRequest -> currentRequest.userID }
        ) { userState ->
            FriendRequestItem(
                userState = userState,
                onClickOpenProfile = onClickOpenProfile,
                onAcceptButtonClick = onAcceptButtonClick,
                onDeleteButtonClick = onDeleteButtonClick,
                modifier = Modifier.animateItemPlacement()
            )
        }

    }
}