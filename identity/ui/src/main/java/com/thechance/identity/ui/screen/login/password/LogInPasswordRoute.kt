package com.thechance.identity.ui.screen.login.password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.login.LoginViewModel

fun NavController.navigateToLogInPassword() {
    navigate(LOGIN_PASSWORD_ROUTE)
}

const val LOGIN_PASSWORD_ROUTE = "logInPasswordScreen"
fun NavGraphBuilder.logInPasswordRoute(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel
) {
    composable(LOGIN_PASSWORD_ROUTE)
    {
        LogInPasswordScreen(navHostController, loginViewModel)
    }
}
