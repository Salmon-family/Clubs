package com.thechance.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.thechance.ui.screens.chat.chatRoute
import com.thechance.ui.screens.conversation.conversationRoute
import com.thechance.ui.screens.friends.friendsRoute

const val START_DESTINATION = "chat"

@Composable
fun ChatNavGraph(
    navController: NavHostController, startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        chatRoute(navController)
        conversationRoute(navController)
        friendsRoute(navController)
    }
}