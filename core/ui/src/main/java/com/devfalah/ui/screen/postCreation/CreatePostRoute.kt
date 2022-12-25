package com.devfalah.ui.screen.postCreation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postCreation.PostCreationArgs
import com.devfalah.viewmodels.userProfile.ProfileArgs

const val CREATE_POST_SCREEN = "CreatePost"

fun NavController.navigateToPostCreation(clubId: Int = 0) {
    navigate("$CREATE_POST_SCREEN/${clubId}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(route = CREATE_POST_SCREEN,
        arguments = listOf(
            navArgument(PostCreationArgs.GROUP_ID) { NavType.IntType }
        )) {
        PostCreationScreen(navController)

    }
}




