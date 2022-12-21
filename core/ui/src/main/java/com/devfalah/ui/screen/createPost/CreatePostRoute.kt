package com.devfalah.ui.screen.createPost

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CREATE_POST_SCREEN = "CreatePost"
fun NavController.navigateToCreatePost() {
    navigate(CREATE_POST_SCREEN)
}

fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(route = CREATE_POST_SCREEN) {
        CreatePostScreen(navController)
    }
}




