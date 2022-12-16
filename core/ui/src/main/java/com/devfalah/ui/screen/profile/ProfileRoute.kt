package com.devfalah.ui.screen.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


const val profileId = "profileId"
fun NavController.navigateToProfile(id: Int) {
    navigate("${PROFILE_SCREEN}/${id}")
}

const val PROFILE_SCREEN = "PROFILE_SCREEN"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(
        route = "${PROFILE_SCREEN}/{${profileId}}",
        arguments = listOf(
            navArgument(profileId) { NavType.IntType }
        )

    ) {
        ProfileScreen(navController)
    }
}