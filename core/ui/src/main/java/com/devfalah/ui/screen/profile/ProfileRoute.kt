package com.devfalah.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.userProfile.ProfileArgs

const val PROFILE_SCREEN = "PROFILE_SCREEN"
fun NavController.navigateToProfile(id: Int) {
    popBackStack(Screen.Home.screen_route, false)
    navigate("${PROFILE_SCREEN}/${id}")
}

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(
        route = "${PROFILE_SCREEN}/{${ProfileArgs.PROFILE_OWNER_ID_ARG}}",
        arguments = listOf(
            navArgument(ProfileArgs.PROFILE_OWNER_ID_ARG) { NavType.IntType }
        )
    ) {
        ProfileScreen(navController)
    }
}