package com.thechance.identity.ui.screen.signup.clubs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavController.navigateToClubs() {
    navigate(route = CLUBS_ROUTE) {
        launchSingleTop = true
    }
}

const val CLUBS_ROUTE = "clubs"
fun NavGraphBuilder.clubsRoute(navHostController: NavHostController) {
    composable(CLUBS_ROUTE) {
        ClubsScreen(navHostController)
    }
}