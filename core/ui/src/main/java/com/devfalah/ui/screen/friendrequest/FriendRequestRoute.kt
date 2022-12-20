package com.devfalah.ui.screen.friendrequest

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val FRIEND_REQUEST_SCREEN = "FriendRequest"
fun NavGraphBuilder.friendRequestRoute(navController: NavController) {
    composable(route = FRIEND_REQUEST_SCREEN) {
        FriendRequestScreen(navController)
    }
}