package com.devfalah.ui.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.screen.menu.composable.AccountSection
import com.devfalah.ui.screen.menu.composable.PreferencesSection
import com.devfalah.ui.screen.menu.composable.TopSection
import com.devfalah.ui.theme.LightBackgroundColor

@Composable
fun MenuScreen(
    navController: NavController,
) {
    MenuContent(
        onClickProfile = {},
        onClickSavedPosts = { navController.navigate(route = Screen.SavedPosts.screen_route) },
        onClickAccountSettings = {},
        onClickFriendsRequests = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) },
        onClickTheme = {},
        onClickLanguage = {}
    )
}

@Composable
fun MenuContent(
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
    MenuContent({}, {}, {}, {}, {}, {}, {})
}