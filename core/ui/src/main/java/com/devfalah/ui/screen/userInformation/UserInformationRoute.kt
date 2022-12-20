package com.devfalah.ui.screen.userInformation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROUTE = "EditUserInformation"

fun NavController.navigateToEditUserInformation() {
    navigate(ROUTE)
}

fun NavGraphBuilder.editUserInformationRoute(navController: NavController) {
    composable(route = ROUTE) {
        UserInformationScreen(navController)
    }
}