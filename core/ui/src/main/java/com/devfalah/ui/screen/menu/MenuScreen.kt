package com.devfalah.ui.screen.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.friendrequest.FRIEND_REQUEST_SCREEN
import com.devfalah.ui.screen.savedPosts.SAVED_SCREEN
import com.devfalah.ui.theme.LightBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.AppBar

@Composable
fun MenuScreen(
    navController: NavController,
) {
    val systemUIController = rememberSystemUiController()

    MenuContent(
        onClickFriends = { navController.navigate(route = FRIEND_REQUEST_SCREEN) },
        onClickSavedPosts = { navController.navigate(route = SAVED_SCREEN) }
        navController,
        onClickFriends = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) },
        onClickSavedPosts = { navController.navigate(route = Screen.SavedPosts.screen_route) }
    )
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = false
        )
    }
}

@Composable
fun MenuContent(
    navController: NavController,
    onClickFriends: () -> Unit,
    onClickSavedPosts: () -> Unit
) {
    Column() {
        AppBar(title = "Menu", navHostController =navController)
        Button(onClick = { onClickFriends() }) {
            Text(text = "Friend Request")
        }

        Button(onClick = { onClickSavedPosts() }) {
            Text(text = "Saved Posts")
        }

    }
}
