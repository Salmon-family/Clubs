package com.thechance.ui.screens.conversation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.thechance.viewmodels.conversation.ConversationArgs
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavController.navigateToConversation(
    id: Int,
    friendId: Int,
    friendName: String,
    friendImage: String,
) {
    val friendImageEncoded = URLEncoder.encode(friendImage, StandardCharsets.UTF_8.toString())
    navigate("$ROUTE/$id/$friendId/$friendName/$friendImageEncoded")
}

private const val ROUTE = "conversationScreen"
fun NavGraphBuilder.conversationRoute(navController: NavHostController) {
    composable(
        route = "$ROUTE/{${ConversationArgs.USER_ID_ARG}}/{${ConversationArgs.FRIEND_ID_ARG}}/{${ConversationArgs.FRIEND_NAME_ARG}}/{${ConversationArgs.FRIEND_IMAGE_ARG}}",
        arguments = listOf(
            navArgument(ConversationArgs.USER_ID_ARG) { NavType.IntType },
            navArgument(ConversationArgs.FRIEND_ID_ARG) { NavType.IntType },
            navArgument(ConversationArgs.FRIEND_NAME_ARG) { NavType.StringType },
            navArgument(ConversationArgs.FRIEND_IMAGE_ARG) { NavType.StringType },
        )
    ) {

        ConversationScreen(navController)
    }
}