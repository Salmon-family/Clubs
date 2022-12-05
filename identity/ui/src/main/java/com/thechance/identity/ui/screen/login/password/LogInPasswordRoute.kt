package com.thechance.identity.ui.screen.login.password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavController.navigateToLogInPassword(){
    navigate(route = LOGIN_PASSWORD_ROUTE)
}

const val LOGIN_PASSWORD_ROUTE = "logInPasswordScreen"
fun NavGraphBuilder.logInPasswordRoute(navHostController: NavHostController) {
    composable(LOGIN_PASSWORD_ROUTE) {
        LogInPasswordScreen(navHostController)
    }
}