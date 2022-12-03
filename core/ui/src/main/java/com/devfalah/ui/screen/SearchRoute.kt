package com.devfalah.ui.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

class SearchRoute {
}

private const val ROUTE_SEARCH = "search"
fun NavGraphBuilder.searchRoute(navController: NavController){
    composable(route = ROUTE_SEARCH){
        SearchScreen(navController)
    }
}