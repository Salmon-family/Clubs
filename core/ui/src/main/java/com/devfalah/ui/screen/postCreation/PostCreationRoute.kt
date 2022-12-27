package com.devfalah.ui.screen.postCreation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postCreation.PostCreationArgs

const val CREATE_POST_SCREEN = "CreatePost"

fun NavController.navigateToPostCreation(clubId: Int) {
    navigate("$CREATE_POST_SCREEN/${clubId}")
}

fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(route = "$CREATE_POST_SCREEN/{${PostCreationArgs.GROUP_ID}}",
        arguments = listOf(
            navArgument(PostCreationArgs.GROUP_ID) { NavType.IntType }
        )) {
        PostCreationScreen(navController)
    }
}




