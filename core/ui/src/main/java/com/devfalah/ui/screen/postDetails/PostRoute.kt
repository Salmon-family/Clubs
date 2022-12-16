package com.devfalah.ui.screen.postDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postDetails.PostDetailsArgs

private const val ROUT = "postDetailsScreen"
fun NavController.navigateToPostDetails(userId: Int, postId: Int) {
    navigate("$ROUT/${userId}/${postId}")
}

fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(route = "$ROUT/{${PostDetailsArgs.USER_ID}}/{${PostDetailsArgs.POST_ID}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.USER_ID) { NavType.IntType },
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType },

            )) {
//        PostDetailsScreen(navController)
    }
}
