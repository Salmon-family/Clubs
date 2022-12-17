package com.devfalah.ui.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.screen.menu.composable.AccountSection
import com.devfalah.ui.screen.menu.composable.PreferencesSection
import com.devfalah.ui.screen.menu.composable.TopSection
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.menu.MenuViewModel
import com.devfalah.viewmodels.menu.UserUiState

@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    MenuContent(
        state = state,
        onClickProfile = { navController.navigateToProfile(state.userId) },
        onClickSavedPosts = { navController.navigate(route = Screen.SavedPosts.screen_route) },
        onClickAccountSettings = { navController.navigate(route = Screen.AccountSettings.screen_route) },
        onClickFriendsRequests = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) },
        onClickTheme = {},
        onClickLanguage = {}
    )
}

@Composable
fun MenuContent(
    state: UserUiState,
    onClickProfile: () -> Unit,
    onClickSavedPosts: () -> Unit,
    onClickAccountSettings: () -> Unit,
    onClickFriendsRequests: () -> Unit,
    onClickTheme: () -> Unit,
    onClickLanguage: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            TopSection(
                state = state,
                onClickProfile = onClickProfile,
                onClickSavedThreads = onClickSavedPosts
            )
        }

        item {
            AccountSection(
                onClickAccountSettings = onClickAccountSettings,
                onClickFriendsRequests = onClickFriendsRequests,
            )
        }

        item {
            PreferencesSection(
                onClickTheme = onClickTheme,
                onClickLanguage = onClickLanguage
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenu() {
    MenuContent(UserUiState(), {}, {}, {}, {}, {}, {})
}