package com.devfalah.ui.screen.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devfalah.ui.Profile


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(route = Profile) {
        ProfileScreen(navController)
    }
}