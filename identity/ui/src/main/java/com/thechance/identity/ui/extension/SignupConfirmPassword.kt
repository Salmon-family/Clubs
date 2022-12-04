package com.thechance.identity.ui.extension

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.signup.SignUpConfirmPasswordScreen

fun NavController.navigateToSignupConfirmPassword(){
    navigate(route = SIGNUP_CONFIRM_PASSWORD_Route)
}

const val SIGNUP_CONFIRM_PASSWORD_Route = "signupConfirmPasswordScreen"
fun NavGraphBuilder.signupConfirmPasswordRoute(navHostController: NavHostController) {
    composable(SIGNUP_CONFIRM_PASSWORD_Route) {
        SignUpConfirmPasswordScreen(
            navHostController
        )
    }
}
