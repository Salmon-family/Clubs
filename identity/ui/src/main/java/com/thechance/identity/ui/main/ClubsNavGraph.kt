package com.thechance.identity.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thechance.identity.ui.screen.activation.accountActivationRoute
import com.thechance.identity.ui.screen.home.homeRoute
import com.thechance.identity.ui.screen.login.password.logInPasswordRoute
import com.thechance.identity.ui.screen.login.username.logInUserNameRoute
import com.thechance.identity.ui.screen.signup.clubs.clubsRoute
import com.thechance.identity.ui.screen.onboarding.pager.ON_BOARDING_PAGER_ROUTE
import com.thechance.identity.ui.screen.onboarding.pager.onBoardingPagerRoute
import com.thechance.identity.ui.screen.onboarding.welcome.WELCOME_ON_BOARDING_ROUTE
import com.thechance.identity.ui.screen.onboarding.welcome.welcomeOnBoardRoute
import com.thechance.identity.ui.screen.signup.birthdate.signupBirthDateAndGanderRoute
import com.thechance.identity.ui.screen.signup.confirmpassword.signupConfirmPasswordRoute
import com.thechance.identity.ui.screen.signup.email.signupEmailRoute
import com.thechance.identity.ui.screen.signup.name.signupNamesRoute
import com.thechance.identity.viewmodel.login.LoginViewModel
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.start.StartInstallViewModel

@Composable
fun ClubsNavGraph(
    navController: NavHostController,
    signupViewModel: SignupViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    startInstallViewModel: StartInstallViewModel = hiltViewModel()
) {
    val startRoute = if (startInstallViewModel.getStartInstall() == true) {
        ON_BOARDING_PAGER_ROUTE
    } else {
        WELCOME_ON_BOARDING_ROUTE
    }
    NavHost(navController = navController, startDestination = startRoute) {
        welcomeOnBoardRoute(navController)
        onBoardingPagerRoute(navController)
        signupEmailRoute(navController, signupViewModel)
        signupConfirmPasswordRoute(navController, signupViewModel)
        signupNamesRoute(navController, signupViewModel)
        signupBirthDateAndGanderRoute(navController, signupViewModel)
        logInUserNameRoute(navController, loginViewModel)
        logInPasswordRoute(navController, loginViewModel)
        homeRoute(navController)
        accountActivationRoute(navController)
        clubsRoute(navController)
    }
}