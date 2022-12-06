package com.thechance.identity.ui.screen.signup.email

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


fun NavController.navigateToSignupEmail() {
    navigate(route = SIGNUP_EMAIL_Route)
}

const val SIGNUP_EMAIL_Route = "signupEmailScreen"
fun NavGraphBuilder.signupEmailRoute(navHostController: NavHostController) {
    composable(SIGNUP_EMAIL_Route) {
        SignUpEmailScreen(navHostController)
    }
}