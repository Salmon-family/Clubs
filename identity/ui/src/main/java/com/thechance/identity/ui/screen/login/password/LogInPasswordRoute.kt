package com.thechance.identity.ui.screen.login.password

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.login.password.LoginPasswordArgs

fun NavController.navigateToLogInPassword(userName: String){
    navigate("$LOGIN_PASSWORD_ROUTE/$userName")
}

const val LOGIN_PASSWORD_ROUTE = "logInPasswordScreen"
fun NavGraphBuilder.logInPasswordRoute(navHostController: NavHostController) {
    composable(
        "$LOGIN_PASSWORD_ROUTE/{${LoginPasswordArgs.USER_NAME_ARG}}",
        arguments = listOf(
            navArgument(LoginPasswordArgs.USER_NAME_ARG){NavType.StringType}
        )
    ) {
        LogInPasswordScreen(navHostController)
    }
}
