package com.thechance.ui.screens.conversation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.thechance.viewmodels.conversation.ConversationArgs

fun NavController.navigateToConversation(friendId: Int) {
    navigate("$ROUTE/$friendId")
}

private const val ROUTE = "conversationScreen"
fun NavGraphBuilder.conversationRoute(
    navController: NavHostController, ) {
    composable(
        route =
        "$ROUTE/{${ConversationArgs.FRIEND_ID_ARG}}",
        arguments = listOf(
            navArgument(ConversationArgs.FRIEND_ID_ARG) { NavType.IntType },
        )
    ) {
        ConversationScreen(navController)
    }
}