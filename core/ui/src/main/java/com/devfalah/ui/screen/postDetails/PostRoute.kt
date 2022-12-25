package com.devfalah.ui.screen.postDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postDetails.PostDetailsArgs
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


const val POST_DETAILS_SCREEN = "POST_DETAILS_SCREEN"
fun NavController.navigateToPostDetails(
    id: Int, isSaved: Boolean, publisherId: Int, publisherName: String, publisherUrl: String
) {
//    popBackStack(Screen.Home.screen_route, false)
    val encodedPublisherImageUrl =
        URLEncoder.encode(publisherUrl, StandardCharsets.UTF_8.toString())
    navigate("${POST_DETAILS_SCREEN}/${id}/${isSaved}/${publisherId}/${publisherName}/${encodedPublisherImageUrl}")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(
        route = "${POST_DETAILS_SCREEN}/{${PostDetailsArgs.POST_ID}}/{${PostDetailsArgs.POST_SAVED}}/{${PostDetailsArgs.PUBLISHER_ID}}/{${PostDetailsArgs.PUBLISHER_NAME}}/{${PostDetailsArgs.PUBLISHER_IMAGE_URL}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType },
            navArgument(PostDetailsArgs.POST_SAVED) { NavType.BoolType },
            navArgument(PostDetailsArgs.PUBLISHER_ID) { NavType.IntType },
            navArgument(PostDetailsArgs.PUBLISHER_NAME) { NavType.StringType },
            navArgument(PostDetailsArgs.PUBLISHER_IMAGE_URL) { NavType.StringType },
        )
    ) {
        PostDetailsScreen(navController)
    }
}