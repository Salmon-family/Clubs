package com.devfalah.ui.screen.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devfalah.ui.Screen

@Composable
fun MenuScreen(
    navController: NavController,
) {

    MenuContent(onClickFriends = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) })
}

@Composable
fun MenuContent(
    onClickFriends: () -> Unit,
) {
    Column() {
        Text(text = "Menu", fontSize = 24.sp)

        Button(onClick = { onClickFriends() }) {
            Text(text = "Friend Request")
        }

    }
}
