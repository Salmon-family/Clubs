package com.devfalah.ui.screen.clubRequests

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.clubRequests.ClubRequestsArgs


const val CLUB_REQUESTS_SCREEN = "CLUB_REQUESTS_SCREEN"

fun NavController.navigateToClubRequests(clubId: Int) {
    navigate("${CLUB_REQUESTS_SCREEN}/${clubId}")
}

fun NavGraphBuilder.clubRequestsRoute(navController: NavController) {
    composable(
        route = "${CLUB_REQUESTS_SCREEN}/{${ClubRequestsArgs.CLUB_ID_ARG}}",
        arguments = listOf(
            navArgument(ClubRequestsArgs.CLUB_ID_ARG) { NavType.IntType },
        )

    ) {
        ClubRequestsScreen(navController)
    }
}