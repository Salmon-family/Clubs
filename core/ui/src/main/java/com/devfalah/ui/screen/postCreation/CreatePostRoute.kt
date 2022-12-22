package com.devfalah.ui.screen.postCreation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CREATE_POST_SCREEN = "CreatePost"

fun NavController.navigateToPostCreation(fromRout: String) {
    navigate(CREATE_POST_SCREEN)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.createPostRoute(navController: NavController) {
    composable(route = CREATE_POST_SCREEN) {
        PostCreationScreen(navController)
    }
}




