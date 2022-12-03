package com.devfalah.ui.screen.friendrequest

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val FriendRequestRoute = "FriendRequest"

fun NavGraphBuilder.friendRequestRoute(navController: NavController) {
    composable(route = FriendRequestRoute) {
        FriendRequestScreen(navController)
    }
}