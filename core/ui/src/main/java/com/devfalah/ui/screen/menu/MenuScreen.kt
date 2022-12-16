package com.devfalah.ui.screen.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.screen.createPost.CREATE_POST_SCREEN
import com.devfalah.ui.screen.friendrequest.FRIEND_REQUEST_SCREEN
import com.devfalah.ui.screen.savedPosts.SAVED_SCREEN

@Composable
fun MenuScreen(
    navController: NavController,
) {

    MenuContent(
        onClickFriends = { navController.navigate(route = FRIEND_REQUEST_SCREEN) },
        onClickSavedPosts = { navController.navigate(route = SAVED_SCREEN) }
    )
}

@Composable
fun MenuContent(
    onClickFriends: () -> Unit,
    onClickSavedPosts: () -> Unit
) {
    Column() {
        Text(text = "Menu", fontSize = 24.sp)

        Button(onClick = { onClickFriends() }) {
            Text(text = "Friend Request")
        }

        Button(onClick = { onClickSavedPosts() }) {
            Text(text = "Saved Posts")
        }

    }
}
