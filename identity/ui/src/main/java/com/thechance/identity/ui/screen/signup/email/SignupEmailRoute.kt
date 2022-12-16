package com.thechance.identity.ui.screen.signup.email

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.signup.SignupViewModel


fun NavController.navigateToSignupEmail() {
    navigate(route = SIGNUP_EMAIL_Route){
        launchSingleTop = true
    }
}

const val SIGNUP_EMAIL_Route = "signupEmailScreen"
fun NavGraphBuilder.signupEmailRoute(navHostController: NavHostController, viewModel: SignupViewModel) {
    composable(SIGNUP_EMAIL_Route) {
        SignUpEmailScreen(navHostController, viewModel)
    }
}
