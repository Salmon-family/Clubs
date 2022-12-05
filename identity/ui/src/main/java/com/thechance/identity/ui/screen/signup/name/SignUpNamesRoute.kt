package com.thechance.identity.ui.screen.signup.name

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


fun NavController.navigateToSignupNames() {
    navigate(route = SIGNUP_USER_INFORMATION_Route)
}

const val SIGNUP_USER_INFORMATION_Route = "signupFirstNameScreen"

fun NavGraphBuilder.signupNamesRoute(navHostController: NavHostController) {
    composable(SIGNUP_USER_INFORMATION_Route) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignUpFullNameScreen(
                navController = navHostController,
            )
        }
    }
}