package com.devfalah.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE_HOME = "home"
fun NavGraphBuilder.homeRoute(navController: NavController){
    composable(route = ROUTE_HOME){
        HomeScreen(navController)
    }
}