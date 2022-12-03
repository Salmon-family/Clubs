package com.devfalah.ui.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE_CLUBS = "clubs"
fun NavGraphBuilder.clubsRoute(navController: NavController){
    composable(route = ROUTE_CLUBS){
        ClubsScreen(navController)
    }
}