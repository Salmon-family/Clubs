package com.thechance.identity.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thechance.identity.ui.screen.home.homeRoute
import com.thechance.identity.ui.screen.login.password.logInPasswordRoute
import com.thechance.identity.ui.screen.login.username.logInUserNameRoute
import com.thechance.identity.ui.screen.onboarding.pager.onBoardingPagerRoute
import com.thechance.identity.ui.screen.onboarding.welcome.WELCOME_ON_BOARDING_Route
import com.thechance.identity.ui.screen.onboarding.welcome.welcomeOnBoardRoute
import com.thechance.identity.ui.screen.signup.birthdate.signupBirthDateAndGanderRoute
import com.thechance.identity.ui.screen.signup.confirmpassword.signupConfirmPasswordRoute
import com.thechance.identity.ui.screen.signup.email.signupEmailRoute
import com.thechance.identity.ui.screen.signup.name.signupNamesRoute

@Composable
fun ClubsNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = WELCOME_ON_BOARDING_Route) {
        welcomeOnBoardRoute(navController)
        onBoardingPagerRoute(navController)
        signupEmailRoute(navController)
        signupConfirmPasswordRoute(navController)
        signupNamesRoute(navController)
        signupBirthDateAndGanderRoute(navController)
        logInUserNameRoute(navController)
        logInPasswordRoute(navController)
        homeRoute(navController)
    }
}