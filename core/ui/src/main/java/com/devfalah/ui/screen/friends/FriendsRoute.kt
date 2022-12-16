package com.devfalah.ui.screen.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.friends.FriendsArgs

fun NavController.navigateToFriends(id: Int) {
    navigate("${FRIENDS_SCREEN}/${id}")
}

const val FRIENDS_SCREEN = "FRIENDS_SCREEN"
fun NavGraphBuilder.friendsRoute(navController: NavController) {
    composable(
        route = "${FRIENDS_SCREEN}/{${FriendsArgs.USER_ID_ARG}}",
        arguments = listOf(
            navArgument(FriendsArgs.USER_ID_ARG) { NavType.IntType }
        )

    ) {
        FriendsScreen(navController)
    }
}