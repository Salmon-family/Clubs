package com.devfalah.ui.screen.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen

const val profileId = "profileId"
fun NavController.navigateToFriends(id: Int) {
    navigate("${Screen.Friends}/${id}")
}

fun NavGraphBuilder.friendsRoute(navController: NavController) {
    composable(
        route = "${Screen.Friends}/{${profileId}}",
        arguments = listOf(
            navArgument(profileId) { NavType.IntType }
        )

    ) {
        FriendsScreen(navController)
    }
}