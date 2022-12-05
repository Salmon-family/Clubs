package com.thechance.ui.screens.conversation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.thechance.viewmodels.conversation.ConversationArgs

fun NavController.navigateToConversation(id: Int){
    navigate("$ROUTE/$id")
}

private const val ROUTE = "conversationScreen"
fun NavGraphBuilder.conversationRoute(navController: NavHostController){
    composable(
        route = "$ROUTE/{${ConversationArgs.ID_ARG}}",
        arguments = listOf(
            navArgument(ConversationArgs.ID_ARG){NavType.StringType}
        )
    ) {

        ConversationScreen(navController)
    }
}