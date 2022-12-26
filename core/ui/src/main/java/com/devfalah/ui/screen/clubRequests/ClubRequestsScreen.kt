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
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.devfalah.ui.screen.friendrequest.friendcomposable.FriendRequestItem
import com.devfalah.ui.screen.profile.navigateToProfile
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
        onRetry = viewModel::getData,
        onClickOpenProfile = { navController.navigateToProfile(it) }
    )
//    LaunchedEffect(true) {
//        setStatusBarColor(
//            systemUIController = systemUIController,
//            color = LightBackgroundColor,
//            darkIcons = true
//        )
//    }
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
    onRetry: () -> Unit
) {
    Column {
        AppBar(title = stringResource(R.string.users_requests), navHostController = navController)

        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            LottieItem(LottieResource = R.raw.loading)
        } else if (state.users.isEmpty()) {
            LottieItem(LottieResource = R.raw.no_data)
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
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
}