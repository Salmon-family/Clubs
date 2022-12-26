package com.thechance.ui.screens.conversation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.thechance.viewmodels.conversation.ConversationArgs

fun NavController.navigateToConversation(id: Int, friendId: Int) {
    navigate("$ROUTE/$id/$friendId")
}

private const val ROUTE = "conversationScreen"
fun NavGraphBuilder.conversationRoute(
    navController: NavHostController, ) {
    composable(
        route =
        "$ROUTE/{${ConversationArgs.USER_ID_ARG}}/{${ConversationArgs.FRIEND_ID_ARG}}",
        arguments = listOf(
            navArgument(ConversationArgs.USER_ID_ARG) { NavType.IntType },
            navArgument(ConversationArgs.FRIEND_ID_ARG) { NavType.IntType },
        )
    ) {
        ConversationScreen(navController)
    }
}