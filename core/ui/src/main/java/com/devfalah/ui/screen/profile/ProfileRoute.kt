package com.devfalah.ui.screen.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.ui.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets



const val profileId = "profileId"
fun NavController.navigateToProfile(id: Int) {
    navigate("${Screen.Profile}/${id}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(
        route = "${Screen.Profile}/{${profileId}}",
        arguments = listOf(
            navArgument(profileId) { NavType.IntType }
        )

    ) {
        ProfileScreen(navController)
    }
}