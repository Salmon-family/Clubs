package com.devfalah.ui.screen.clubCreation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CLUB_CREATION_ROUTE = "ClubCreation"

fun NavController.navigateToClubCreation() {
    navigate(CLUB_CREATION_ROUTE)
}

fun NavGraphBuilder.clubCreationRoute(navController: NavController) {
    composable(route = CLUB_CREATION_ROUTE) {
        ClubCreationScreen(navController)
    }
}