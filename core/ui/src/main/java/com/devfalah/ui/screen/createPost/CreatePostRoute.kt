package com.devfalah.ui.screen.createPost

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.createPost.publisherId

fun NavController.navigateToCreatePost(id: Int) {
    navigate("${Screen.CreatePost}/${id}")
}

fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(
        route = "${Screen.CreatePost}/{${publisherId}}",
        arguments = listOf(
            navArgument(publisherId) { NavType.IntType }
        )) {
        CreatePostScreen(navController)
    }
}




