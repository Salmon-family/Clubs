package com.devfalah.ui.screen.postDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen

const val postId = "postId"
fun NavController.navigateToPostDetails(id: Int) {
    navigate("${Screen.Profile}/${id}")
}

fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(route = "${Screen.PostDetails}/{$postId}", arguments = listOf(
        navArgument(postId) { NavType.IntType }
    )) {
        PostDetailsScreen(navController)
    }
}