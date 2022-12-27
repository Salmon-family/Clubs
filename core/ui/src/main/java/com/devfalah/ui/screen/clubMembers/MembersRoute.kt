package com.devfalah.ui.screen.clubMembers

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.clubMembers.ClubMembersArgs


const val CLUB_MEMBERS_SCREEN = "CLUB_MEMBERS_SCREEN"

fun NavController.navigateToMembers(clubId: Int, ownerId: Int) {
    navigate("${CLUB_MEMBERS_SCREEN}/${clubId}/${ownerId}")
}

fun NavGraphBuilder.membersRoute(navController: NavController) {
    composable(
        route = "${CLUB_MEMBERS_SCREEN}/{${ClubMembersArgs.CLUB_ID}}/{${ClubMembersArgs.OWNER_ID}}",
        arguments = listOf(
            navArgument(ClubMembersArgs.CLUB_ID) { NavType.IntType }
        )
    ) {
        MembersScreen(navController)
    }
}