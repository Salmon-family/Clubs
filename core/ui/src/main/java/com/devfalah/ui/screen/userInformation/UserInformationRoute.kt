package com.devfalah.ui.screen.userInformation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.devfalah.ui.screen.profile.PROFILE_SCREEN


const val ROUTE = "EditUserInformation"

fun NavController.navigateToEditUserInformation() {
    navigate(ROUTE){
        popUpTo(PROFILE_SCREEN) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.editUserInformationRoute(navController: NavController) {
    composable(route = ROUTE) {
        UserInformationScreen(navController)
    }
}