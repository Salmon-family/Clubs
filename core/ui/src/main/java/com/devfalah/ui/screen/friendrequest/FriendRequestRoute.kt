package com.devfalah.ui.screen.friendrequest

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.FriendRequestRoute

fun NavGraphBuilder.friendRequestRoute(navController: NavController) {
    composable(route = FriendRequestRoute) {
        FriendRequestScreen(navController)
    }
}