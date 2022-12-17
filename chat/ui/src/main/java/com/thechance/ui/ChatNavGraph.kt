package com.thechance.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thechance.ui.screens.chat.chatRoute
import com.thechance.ui.screens.conversation.conversationRoute

const val START_DESTINATION = "chat"

@Composable
fun ChatNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = START_DESTINATION) {
        chatRoute(navController)
        conversationRoute(navController)
    }
}