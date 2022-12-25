package com.thechance.identity.ui.screen.signup.jobtitle

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.identity.viewmodel.signup.SignupViewModel

fun NavController.navigateToJobTitle(){
    navigate(route = SIGNUP_JOB_TITLE_ROUTE){
        launchSingleTop = true
    }
}

const val SIGNUP_JOB_TITLE_ROUTE = "signupJobTitleScreen"

fun NavGraphBuilder.signupJobTitleRoute(
    navHostController: NavHostController,
    viewModel: SignupViewModel
){
    composable(SIGNUP_JOB_TITLE_ROUTE){
        JobTitleScreen(
            navController = navHostController,
            viewModel
        )
    }
}