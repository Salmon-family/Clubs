package com.thechance.identity.ui.extension

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.onboarding.WelcomeOnboardScreen

const val WELCOME_ON_BOARDING_Route = "welcomeOnBoard"
fun NavGraphBuilder.welcomeOnBoardRoute(navHostController: NavHostController) {
    composable(WELCOME_ON_BOARDING_Route) {
        WelcomeOnboardScreen(navHostController)
    }
}