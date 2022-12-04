package com.thechance.identity.ui.extension

import android.os.Build
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.signup.SignUpBirthdateAndGenderScreen


fun NavController.navigateToBirthdateAndGander() {
    navigate(route = SIGNUP_BIRTHDATE_AND_GANDER_Route)
}

const val SIGNUP_BIRTHDATE_AND_GANDER_Route = "signupBirthdateAndGanderScreen"

fun NavGraphBuilder.signupBirthDateAndGanderRoute(navHostController: NavHostController) {
    composable(SIGNUP_BIRTHDATE_AND_GANDER_Route) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SignUpBirthdateAndGenderScreen(
                navController = navHostController,
            )
        }
    }
}