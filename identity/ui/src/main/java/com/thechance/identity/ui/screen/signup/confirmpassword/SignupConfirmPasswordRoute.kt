package com.thechance.identity.ui.screen.signup.confirmpassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.signup.SignupViewModel

fun NavController.navigateToSignupConfirmPassword(){
    navigate(route = SIGNUP_CONFIRM_PASSWORD_Route)
}

const val SIGNUP_CONFIRM_PASSWORD_Route = "signupConfirmPasswordScreen"
fun NavGraphBuilder.signupConfirmPasswordRoute(navHostController: NavHostController,viewModel: SignupViewModel) {
    composable(SIGNUP_CONFIRM_PASSWORD_Route) {
        SignUpConfirmPasswordScreen(
            navHostController,
            viewModel
        )
    }
}
