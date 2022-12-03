package com.thechance.identity.ui.util.extension

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.signup.SignUpEmailScreen


fun NavController.navigateToSignupEmail() {
    navigate(route = SIGNUP_EMAIL_Route)
}

const val SIGNUP_EMAIL_Route = "signupEmailScreen"
fun NavGraphBuilder.signupEmailRoute(navHostController: NavHostController) {
    composable(SIGNUP_EMAIL_Route) {
        SignUpEmailScreen(navHostController)
    }
}
