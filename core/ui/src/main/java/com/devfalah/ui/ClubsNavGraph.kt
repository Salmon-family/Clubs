package com.devfalah.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devfalah.ui.screen.*
import com.devfalah.ui.screen.search.searchRoute


@Composable
fun ClubsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.screen_route
    ) {

        homeRoute(navController = navController)
        searchRoute(navController = navController)
        clubsRoute(navController= navController)
        notificationRoute(navController = navController)
        menuRoute(navController = navController)

    }
}