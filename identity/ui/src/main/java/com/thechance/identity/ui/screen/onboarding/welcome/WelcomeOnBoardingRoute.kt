package com.thechance.identity.ui.screen.onboarding.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val WELCOME_ON_BOARDING_ROUTE = "welcomeOnBoard"
fun NavGraphBuilder.welcomeOnBoardRoute(navHostController: NavHostController) {
    composable(WELCOME_ON_BOARDING_ROUTE) {
        WelcomeOnboardScreen(navHostController)
    }
}