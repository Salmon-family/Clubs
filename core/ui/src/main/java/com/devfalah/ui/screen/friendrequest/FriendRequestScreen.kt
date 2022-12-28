package com.devfalah.ui.screen.friendrequest

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
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
import com.devfalah.ui.composable.ErrorItem
import com.devfalah.ui.composable.LottieItem
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
    viewModel: FriendRequestViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    FriendRequestsContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onAcceptButtonClick = viewModel::acceptFriendRequest,
        onDeleteButtonClick = viewModel::deniedFriendRequest,
        onRetry = viewModel::getData,
        onClickOpenProfile = { navController.navigateToProfile(it) },
    )
    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun FriendRequestsContent(
    state: FriendRequestUiState,
    onClickBack: () -> Unit,
    onAcceptButtonClick: (Int) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    onClickOpenProfile: (Int) -> Unit,
    onRetry: () -> Unit,
) {
    Column {
        AppBar(
            title = stringResource(R.string.friends_request),
            onBackButton = onClickBack,
        )
        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            LottieItem(LottieResource = R.raw.loading)
        } else if (state.friendRequests.isEmpty()) {
            LottieItem(LottieResource = R.raw.no_data)
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .background(LightBackgroundColor)
                    .fillMaxSize(),
            ) {
                items(
                    items = state.friendRequests,
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
}