package com.devfalah.ui.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.friendrequest.FRIEND_REQUEST_SCREEN
import com.devfalah.ui.screen.savedPosts.SAVED_SCREEN
import com.devfalah.ui.theme.LightBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.devfalah.ui.Screen
import com.devfalah.ui.screen.menu.composable.AccountSection
import com.devfalah.ui.screen.menu.composable.MenuItem
import com.devfalah.ui.screen.menu.composable.PreferencesSection
import com.devfalah.ui.screen.menu.composable.TopSection
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.menu.MenuViewModel
import com.devfalah.viewmodels.menu.UserUiState
import com.devfalah.ui.composable.AppBar

@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    MenuContent(
        state = state,
        onClickProfile = { navController.navigateToProfile(state.userId) },
        onClickSavedPosts = { navController.navigate(route = Screen.SavedPosts.screen_route) },
        onClickAccountSettings = { navController.navigate(route = Screen.AccountSettings.screen_route) },
        onClickFriendsRequests = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) },
        onClickTheme = {},
        onClickLanguage = {},
        onClickReportBug = {}
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
fun MenuContent(
    state: UserUiState,
    onClickProfile: () -> Unit,
    onClickSavedPosts: () -> Unit,
    onClickAccountSettings: () -> Unit,
    onClickFriendsRequests: () -> Unit,
    onClickTheme: () -> Unit,
    onClickLanguage: () -> Unit,
    onClickReportBug: () -> Unit
) {
    AppBar(title = "Menu", navHostController =navController)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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

        item {
            MenuItem(
                text = stringResource(R.string.report_bugs),
                painter = painterResource(id = R.drawable.ic_menu_bug),
                onClickItem = onClickReportBug
            )
        }

        item {
            Text(
                text = "version would be shown here",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = LightTernaryBlackColor,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenu() {
    MenuContent(UserUiState(), {}, {}, {}, {}, {}, {}, {})
}