package com.devfalah.ui.screen.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.friends.friendsUserId

fun NavController.navigateToFriends(id: Int) {
    navigate("${FRIENDS_SCREEN}/${id}")
}

const val FRIENDS_SCREEN = "FRIENDS_SCREEN"
fun NavGraphBuilder.friendsRoute(navController: NavController) {
    composable(
        route = "${FRIENDS_SCREEN}/{${friendsUserId}}",
        arguments = listOf(
            navArgument(friendsUserId) { NavType.IntType }
        )

    ) {
        FriendsScreen(navController)
    }
}