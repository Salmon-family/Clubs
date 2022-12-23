package com.devfalah.ui.screen.postDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.postDetails.PostDetailsArgs


const val POST_DETAILS = "POST_DETAILS"
fun NavController.navigateToPostDetails(id: Int, isSaved: Boolean) {
    popBackStack(Screen.Home.screen_route, false)
    navigate("${POST_DETAILS}/${id}/${isSaved}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(
        route = "${POST_DETAILS}/{${PostDetailsArgs.POST_ID}}/{${PostDetailsArgs.POST_SAVED}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType },
            navArgument(PostDetailsArgs.POST_SAVED) { NavType.BoolType }
        )
    ) {
        PostDetailsScreen(navController)
    }
}