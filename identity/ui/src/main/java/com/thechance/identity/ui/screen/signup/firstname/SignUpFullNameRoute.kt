package com.thechance.identity.ui.screen.signup.firstname

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.signup.SignUpFullNameScreen


fun NavController.navigateToSignupFullName() {
    navigate(route = SIGNUP_USER_INFORMATION_Route)
}

const val SIGNUP_USER_INFORMATION_Route = "signupFirstNameScreen"

fun NavGraphBuilder.signupFullNameRoute(navHostController: NavHostController) {
    composable(SIGNUP_USER_INFORMATION_Route) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignUpFullNameScreen(
                navController = navHostController,
            )
        }
    }
}