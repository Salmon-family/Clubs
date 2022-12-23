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
fun NavController.navigateToPostDetails(id: Int) {
    popBackStack(Screen.Home.screen_route, false)
    navigate("${POST_DETAILS}/${id}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(
        route = "${POST_DETAILS}/{${PostDetailsArgs.POST_ID}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType }
        )
    ) {
        PostDetailsScreen(navController)
    }
}