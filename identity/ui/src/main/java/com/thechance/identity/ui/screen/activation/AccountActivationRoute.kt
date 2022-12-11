package com.thechance.identity.ui.screen.activation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavController.navigateToAccountActivation() {
    navigate(route = ACCOUNT_ACTIVATION_Route) {
        launchSingleTop = true
    }
}

const val ACCOUNT_ACTIVATION_Route = "accountActivationScreen"
fun NavGraphBuilder.accountActivationRoute(navHostController: NavHostController) {
    composable(ACCOUNT_ACTIVATION_Route) {
        AccountActivationScreen(
            navHostController
        )
    }
}
