package com.devfalah.ui.screen.postDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postDetails.PostDetailsArgs

private const val ROUT = "postDetailsScreen"
fun NavController.navigateToPostDetails(postId: Int) {
    navigate("$ROUT/${postId}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(route = "$ROUT/{${PostDetailsArgs.POST_ID}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType },
            )) {
        PostDetailsScreen(navController)
    }
}
