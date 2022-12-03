package com.devfalah.ui.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE_NOTIFICATION = "notification"
fun NavGraphBuilder.notificationRoute(navController: NavController){
    composable(route =ROUTE_NOTIFICATION){
        NotificationScreen(navController)
    }
}