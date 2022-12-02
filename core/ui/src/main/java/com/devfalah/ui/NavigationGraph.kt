package com.devfalah.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devfalah.ui.screen.HomeScreen
import com.devfalah.ui.screen.NotificationScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screen_route) {
        composable(route = BottomNavItem.Home.screen_route){
            HomeScreen()
        }
        composable(route = BottomNavItem.Notification.screen_route){
            NotificationScreen()
        }
    }
}