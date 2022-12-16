package com.thechance.ui.screens.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thechance.ui.START_DESTINATION


private const val ROUTE = START_DESTINATION
fun NavGraphBuilder.chatRoute(navController: NavHostController){
    composable(
        route = ROUTE
    ) {
        ChatsScreen(navController)
    }
}