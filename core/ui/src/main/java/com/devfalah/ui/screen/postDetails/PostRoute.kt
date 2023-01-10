package com.devfalah.ui.screen.postDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postDetails.PostDetailsArgs


const val POST_DETAILS_SCREEN = "postDetailsScreenWithId"

fun NavController.navigateToPostDetails(id: Int) {
    navigate("${POST_DETAILS_SCREEN}/${id}")
}

fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(
        route = "${POST_DETAILS_SCREEN}/{${PostDetailsArgs.POST_ID}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType },
        )
    ) {
        PostDetailsScreen(navController)
    }
}