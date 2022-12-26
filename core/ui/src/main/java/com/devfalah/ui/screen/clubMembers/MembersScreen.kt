package com.devfalah.ui.screen.clubMembers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.clubMembers.ClubMembersViewModel
import com.devfalah.viewmodels.friends.FriendsUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MembersScreen(
    navController: NavController,
    viewModel: ClubMembersViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    ClubMembersContent(
        state = state,
        navController = navController,
        onRefresh = viewModel::getClubMembers,
        onClickProfile = { navController.navigateToProfile(it) },
        onRetry = viewModel::getClubMembers,
        onRemoveFriend = {}
    )
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
        )
    }
}


@Composable
fun ClubMembersContent(
    state: FriendsUIState,
    navController: NavController,
    onRefresh: () -> Unit,
    onClickProfile: (Int) -> Unit,
    onRemoveFriend: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        AppBar(
            title = stringResource(id = R.string.members),
            navHostController = navController
        )
        if (state.isLoading) {
            LottieItem(LottieResource = R.raw.loading)
        } else if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.friends.isEmpty()) {
            LottieItem(LottieResource = R.raw.no_data)
        } else {
            ManualPager(
                onRefresh = onRefresh,
                contentPadding = PaddingValues(16.dp),
                isLoading = state.isPagerLoading,
                error = state.minorError,
                isEndOfPager = state.isPagerEnd,
            ) {

                if (state.isMyProfile) {
                    items(state.friends) {
                        FriendRemoveItem(
                            state = it,
                            onClickOpenProfile = onClickProfile,
                            onRemoveFriend = onRemoveFriend
                        )
                    }
                } else {
                    items(state.friends) {
                        FriendItem(
                            state = it,
                            onOpenProfileClick = onClickProfile
                        )
                    }
                }
            }
        }
    }
}