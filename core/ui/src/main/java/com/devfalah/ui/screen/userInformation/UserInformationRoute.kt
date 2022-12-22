package com.devfalah.ui.screen.userInformation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.Screen
import com.devfalah.ui.screen.profile.PROFILE_SCREEN


const val ROUTE = "EditUserInformation"

fun NavController.navigateToEditUserInformation() {
    navigate(ROUTE)
}

fun NavGraphBuilder.editUserInformationRoute(navController: NavController) {
    composable(route = ROUTE) {
        UserInformationScreen(navController)
    }
}