package com.devfalah.ui.screen.postDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.PostDetailsRoute

fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(route = PostDetailsRoute) {
//        PostDetailsScreen(navController)
    }
}