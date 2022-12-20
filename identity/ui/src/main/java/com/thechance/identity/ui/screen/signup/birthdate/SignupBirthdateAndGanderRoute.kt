package com.thechance.identity.ui.screen.signup.birthdate

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.signup.SignupViewModel


fun NavController.navigateToBirthdateAndGander() {
    navigate(route = SIGNUP_BIRTHDATE_AND_GANDER_ROUTE){
        launchSingleTop = true
    }
}

const val SIGNUP_BIRTHDATE_AND_GANDER_ROUTE = "signupBirthdateAndGanderScreen"

fun NavGraphBuilder.signupBirthDateAndGanderRoute(navHostController: NavHostController,viewModel: SignupViewModel) {
    composable(SIGNUP_BIRTHDATE_AND_GANDER_ROUTE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignupBirthdateAndGenderScreen(
                navController = navHostController,
                viewModel
            )
        }
    }
}