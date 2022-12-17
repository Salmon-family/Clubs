package com.devfalah.ui.screen.accountSettings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE_ACCOUNT_SETTINGS = "AccountSettings"
fun NavGraphBuilder.accountSettingsRoute(navController: NavController){
    composable(route = ROUTE_ACCOUNT_SETTINGS){
        AccountSettingsScreen(navController)
    }
}