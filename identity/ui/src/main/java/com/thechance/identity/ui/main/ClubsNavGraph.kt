package com.thechance.identity.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thechance.identity.ui.screen.activation.accountActivationRoute
import com.thechance.identity.ui.screen.home.homeRoute
import com.thechance.identity.ui.screen.login.password.logInPasswordRoute
import com.thechance.identity.ui.screen.login.username.logInUserNameRoute
import com.thechance.identity.ui.screen.onboarding.pager.onBoardingPagerRoute
import com.thechance.identity.ui.screen.onboarding.welcome.WELCOME_ON_BOARDING_Route
import com.thechance.identity.ui.screen.onboarding.welcome.welcomeOnBoardRoute
import com.thechance.identity.ui.screen.signup.birthdate.SIGNUP_BIRTHDATE_AND_GANDER_Route
import com.thechance.identity.ui.screen.signup.birthdate.signupBirthDateAndGanderRoute
import com.thechance.identity.ui.screen.signup.confirmpassword.signupConfirmPasswordRoute
import com.thechance.identity.ui.screen.signup.email.signupEmailRoute
import com.thechance.identity.ui.screen.signup.name.signupNamesRoute
import com.thechance.identity.viewmodel.signup.SignupViewModel

@Composable
fun ClubsNavGraph(
    navController: NavHostController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = SIGNUP_BIRTHDATE_AND_GANDER_Route) {
        welcomeOnBoardRoute(navController)
        onBoardingPagerRoute(navController)
        signupEmailRoute(navController, viewModel)
        signupConfirmPasswordRoute(navController, viewModel)
        signupNamesRoute(navController, viewModel)
        signupBirthDateAndGanderRoute(navController, viewModel)
        logInUserNameRoute(navController)
        logInPasswordRoute(navController)
        homeRoute(navController)
        accountActivationRoute(navController)
    }
}