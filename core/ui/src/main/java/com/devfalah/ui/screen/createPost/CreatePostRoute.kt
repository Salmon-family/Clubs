package com.devfalah.ui.screen.createPost

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.Screen

fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(route = Screen.CreatePost.screen_route) {
        CreatePostScreen(navController)
    }
}