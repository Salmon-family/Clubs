package com.devfalah.ui.screen.accountSettings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ACCOUNT_SETTINGS_SCREEN = "AccountSettings"
fun NavGraphBuilder.accountSettingsRoute(navController: NavController){
    composable(route = ACCOUNT_SETTINGS_SCREEN){
        AccountSettingsScreen(navController)
    }
}