package com.devfalah.ui.screen.clubCreation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE = "ClubCreation"

fun NavController.navigateToClubCreation() {
    navigate(ROUTE)
}

fun NavGraphBuilder.clubCreationRoute(navController: NavController) {
    composable(route = ROUTE) {
        ClubCreationScreen(navController)
    }
}