package com.thechance.identity.ui.screen.onboarding.clubs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val CLUBS_ROUTE = "clubs"
fun NavGraphBuilder.clubsRoute(navHostController: NavHostController){
    composable(CLUBS_ROUTE){
        ClubsScreen(navHostController)
    }
}