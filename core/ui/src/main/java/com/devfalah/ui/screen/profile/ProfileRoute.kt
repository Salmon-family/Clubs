package com.devfalah.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.Profile


fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(route = Profile) {
        ProfileScreen(navController)
    }
}