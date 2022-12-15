package com.devfalah.ui.screen.savedPosts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.Screen

fun NavGraphBuilder.savedPostsRoute(navController: NavController) {
    composable(route = Screen.SavedPosts.screen_route) {
        SavedPostsScreen(navController)
    }
}