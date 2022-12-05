package com.thechance.identity.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavController.navigateToHome(){
    navigate(route = Home_ROUTE)
}

const val Home_ROUTE = "homeScreen"
fun NavGraphBuilder.homeRoute(navHostController: NavHostController) {
    composable(Home_ROUTE) {
        HomeScreen(navHostController)
    }
}