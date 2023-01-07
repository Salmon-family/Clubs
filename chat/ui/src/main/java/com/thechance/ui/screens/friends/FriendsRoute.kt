package com.thechance.ui.screens.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


fun NavController.navigateToFriends() {
    navigate(ROUTE)
}

private const val ROUTE = "friendsScreen"
fun NavGraphBuilder.friendsRoute(
    navController: NavHostController,
) {
    composable(
        route =
        ROUTE
    ) {
        FriendsScreen(navController)
    }
}