package com.thechance.identity.ui.screen.onboarding.pager

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


const val ON_BOARDING_PAGER_ROUTE = "onBoardingPagerScreen"
fun NavGraphBuilder.onBoardingPagerRoute(navHostController: NavHostController) {
    composable(ON_BOARDING_PAGER_ROUTE) {
        OnBoardingPagerScreen(navHostController)
    }
}

fun NavController.navigateToOnBoardingPager() {
    navigate(route = ON_BOARDING_PAGER_ROUTE) {
        launchSingleTop = true
    }
}