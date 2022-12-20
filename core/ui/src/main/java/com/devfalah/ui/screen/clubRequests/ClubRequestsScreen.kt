package com.devfalah.ui.screen.clubRequests

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.friendrequest.friendcomposable.FriendRequestItem
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.clubRequests.ClubRequestsUIState
import com.devfalah.viewmodels.clubRequests.ClubRequestsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClubRequestsScreen(
    navController: NavController,
    viewModel: ClubRequestsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    ClubRequestsContent(
        navController,
        state = state,
        onAcceptButtonClick = viewModel::acceptRequest,
        onDeleteButtonClick = viewModel::declineRequest,
        onClickOpenProfile = { navController.navigateToProfile(it) }
    )
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun ClubRequestsContent(
    navController: NavController,
    state: ClubRequestsUIState,
    onAcceptButtonClick: (Int) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    onClickOpenProfile: (Int) -> Unit,
) {
    Column {
        AppBar(title = stringResource(R.string.users_requests), navHostController = navController)
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(LightBackgroundColor)
                .fillMaxSize(),
        ) {
            items(
                items = state.users,
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
}