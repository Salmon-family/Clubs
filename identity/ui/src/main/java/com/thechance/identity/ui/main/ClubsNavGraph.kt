package com.thechance.identity.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thechance.identity.ui.extension.*

@Composable
fun ClubsNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = WELCOME_ON_BOARDING_Route) {
        welcomeOnBoardRoute(navController)
        onBoardingPagerRoute(navController)
        signupEmailRoute(navController)
        signupConfirmPasswordRoute(navController)
        signupFullNameRoute(navController)
        signupUserNameRoute(navController)
        signupBirthDateAndGanderRoute(navController)
        logInUserNameRoute(navController)
        logInPasswordRoute(navController)
        homeRoute(navController)
    }
}