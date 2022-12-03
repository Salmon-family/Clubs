package com.devfalah.ui.screen.menu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE_MENU = "menu"
fun NavGraphBuilder.menuRoute(navController: NavController){
    composable(route = ROUTE_MENU){
        MenuScreen(navController)
    }
}