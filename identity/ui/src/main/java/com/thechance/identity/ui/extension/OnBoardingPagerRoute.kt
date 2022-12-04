package com.thechance.identity.ui.extension

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.ui.screen.onboarding.OnBoardingPagerScreen


const val ON_BOARDING_PAGER_Route = "onBoardingPagerScreen"
fun NavGraphBuilder.onBoardingPagerRoute(navHostController: NavHostController) {
    composable(ON_BOARDING_PAGER_Route) {
        OnBoardingPagerScreen(navHostController)
    }
}

fun NavController.navigateToOnBoardingPager() {
    navigate(route = ON_BOARDING_PAGER_Route)
}