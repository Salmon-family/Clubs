package com.thechance.identity.ui.util.extension

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.signup.SignUpUserInformationScreen


fun NavController.navigateToSignupUserInformation() {
    navigate(route = SIGNUP_USER_INFORMATION_Route)
}

const val SIGNUP_USER_INFORMATION_Route = "signupUserInformationScreen"

fun NavGraphBuilder.signupUserInformationRoute(navHostController: NavHostController) {
    composable(SIGNUP_USER_INFORMATION_Route) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignUpUserInformationScreen(
                navController = navHostController,
            )
        }
    }
}