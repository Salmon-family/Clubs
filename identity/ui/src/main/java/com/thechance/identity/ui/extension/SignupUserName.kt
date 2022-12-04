package com.thechance.identity.ui.extension

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.signup.SignUpUserNameScreen


fun NavController.navigateToUserName() {
    navigate(route = SIGNUP_USER_NAME_Route)
}

const val SIGNUP_USER_NAME_Route = "signupUserNameScreen"

fun NavGraphBuilder.signupUserNameRoute(navHostController: NavHostController) {
    composable(SIGNUP_USER_NAME_Route) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignUpUserNameScreen(
                navController = navHostController,
            )
        }
    }
}