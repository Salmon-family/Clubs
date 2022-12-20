package com.devfalah.ui.screen.friends

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.FriendItem
import com.devfalah.ui.composable.FriendRemoveItem
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.friends.FriendsUIState
import com.devfalah.viewmodels.friends.FriendsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun FriendsScreen(
    navController: NavController,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    FriendsContent(
        state = state,
        onRefresh = viewModel::getUserFriends,
        onClickProfile = { navController.navigateToProfile(it) },
        onRemoveFriend = viewModel::removeFriend
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
fun FriendsContent(
    state: FriendsUIState,
    onRefresh: (Int) -> Unit,
    onClickProfile: (Int) -> Unit,
    onRemoveFriend: (Int) -> Unit
) {
    ManualPager(
        onRefresh = onRefresh,
        contentPadding = PaddingValues(16.dp),
        isLoading = state.isLoading,
        error = state.error,
        isEndOfPager = state.isPagerEnd,
    ) {
        item {
            Text(
                text = "${state.totalFriends} " + stringResource(id = R.string.friends),
                fontFamily = PlusJakartaSans,
                fontSize = 18.sp,
                color = LightSecondaryBlackColor,
                fontWeight = FontWeight.SemiBold
            )
        }
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
