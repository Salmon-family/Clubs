package com.devfalah.ui.screen.editclubscreen

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.devfalah.viewmodels.editclub.EditClubArgs

private const val ROUTE = "EditClub"

fun NavController.navigateToEditClub(
    clubId: Int,
    clubName: String,
    clubPrivacy: Boolean,
    clubDescription: String,
) {
    navigate("$ROUTE/$clubId/$clubName/$clubPrivacy/$clubDescription")
}

fun NavGraphBuilder.editClubRoute(navController: NavHostController) {
    composable(
        route = "$ROUTE/{${EditClubArgs.CLUB_ID_ARG}}/{${EditClubArgs.CLUB_NAME_ARG}}/{${EditClubArgs.PRIVACY_ARG}}/{${EditClubArgs.CLUB_DESCRIPTION}}",
        arguments = listOf(
            navArgument(EditClubArgs.CLUB_ID_ARG) { NavType.IntType },
            navArgument(EditClubArgs.CLUB_NAME_ARG) { NavType.StringType },
            navArgument(EditClubArgs.PRIVACY_ARG) { NavType.BoolType },
            navArgument(EditClubArgs.CLUB_DESCRIPTION) { NavType.StringType },
        )
    ) {
        EditClubScreen(navController)
    }
}