package com.devfalah.ui.screen.savedPosts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.Screen

const val SAVED_SCREEN = "SAVED_SCREEN"
fun NavGraphBuilder.savedPostsRoute(navController: NavController) {
    composable(route = SAVED_SCREEN) {
        SavedPostsScreen(navController)
    }
}