package com.thechance.identity.ui.screen.signup.name

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.signup.SignupViewModel


fun NavController.navigateToSignupNames() {
    navigate(route = SIGNUP_USER_INFORMATION_ROUTE){
        launchSingleTop = true
    }
}

const val SIGNUP_USER_INFORMATION_ROUTE = "signupFirstNameScreen"

fun NavGraphBuilder.signupNamesRoute(
    navHostController: NavHostController,
    viewModel: SignupViewModel
) {
    composable(SIGNUP_USER_INFORMATION_ROUTE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignUpFullNameScreen(
                navController = navHostController,
                viewModel
            )
        }
    }
}