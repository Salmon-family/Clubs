package com.thechance.identity.ui.extension

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.login.LogInUserNameScreen

fun NavController.navigateToLogInUserName(){
    navigate(route = LOGIN_USER_NAME_ROUTE)
}

const val LOGIN_USER_NAME_ROUTE = "logInUserNameScreen"
fun NavGraphBuilder.logInUserNameRoute(navHostController: NavHostController) {
    composable(LOGIN_USER_NAME_ROUTE) {
        LogInUserNameScreen(navHostController)
    }
}