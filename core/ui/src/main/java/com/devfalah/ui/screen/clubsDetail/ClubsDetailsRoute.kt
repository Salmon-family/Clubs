package com.devfalah.ui.screen.clubsDetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.clubDetails.ClubDetailsArgs

fun NavController.navigateToClubDetails(userId: Int, groupId: Int) {
    popBackStack(Screen.Clubs.screen_route, false)
    navigate("${ROUTE_CLUBS_DETAILS}/${userId}/${groupId}")
}

private const val ROUTE_CLUBS_DETAILS = "clubsDetails"
fun NavGraphBuilder.clubsDetailsRoute(navController: NavController) {
    composable(
        route = "${ROUTE_CLUBS_DETAILS}/{${ClubDetailsArgs.USER_ID_ARG}}/{${ClubDetailsArgs.GROUP_ID_ARG}}",
        arguments = listOf(
            navArgument(ClubDetailsArgs.USER_ID_ARG) { NavType.IntType },
            navArgument(ClubDetailsArgs.GROUP_ID_ARG) { NavType.IntType },
        )
    ) {
        ClubsDetailsScreen(navController)
    }
}