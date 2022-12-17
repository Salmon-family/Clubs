package com.devfalah.ui.screen.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.AppBar

@Composable
fun MenuScreen(
    navController: NavController,
) {

    MenuContent(
        navController,
        onClickFriends = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) },
        onClickSavedPosts = { navController.navigate(route = Screen.SavedPosts.screen_route) }
    )
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
