package com.devfalah.ui.screen.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.friends.friendsUserId

fun NavController.navigateToFriends(id: Int) {
    navigate("${Screen.Friends}/${id}")
}

fun NavGraphBuilder.friendsRoute(navController: NavController) {
    composable(
        route = "${Screen.Friends}/{${friendsUserId}}",
        arguments = listOf(
            navArgument(friendsUserId) { NavType.IntType }
        )

    ) {
        FriendsScreen(navController)
    }
}