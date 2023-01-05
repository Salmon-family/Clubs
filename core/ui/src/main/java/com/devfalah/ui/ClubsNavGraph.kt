package com.devfalah.ui

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devfalah.ui.image.ImageActivity
import com.devfalah.ui.screen.accountSettings.accountSettingsRoute
import com.devfalah.ui.screen.allSearchResultScreen.allSearchResultRoute
import com.devfalah.ui.screen.clubCreation.clubCreationRoute
import com.devfalah.ui.screen.clubMembers.membersRoute
import com.devfalah.ui.screen.clubRequests.clubRequestsRoute
import com.devfalah.ui.screen.clubs.clubsRoute
import com.devfalah.ui.screen.clubsDetail.clubsDetailsRoute
import com.devfalah.ui.screen.editclubscreen.editClubRoute
import com.devfalah.ui.screen.friendrequest.friendRequestRoute
import com.devfalah.ui.screen.friends.friendsRoute
import com.devfalah.ui.screen.home.homeRoute
import com.devfalah.ui.screen.menu.menuRoute
import com.devfalah.ui.screen.notification.notificationRoute
import com.devfalah.ui.screen.postCreation.createPostRoute
import com.devfalah.ui.screen.postDetails.postDetailsRoute
import com.devfalah.ui.screen.profile.profileRoute
import com.devfalah.ui.screen.reportBug.reportBugRoute
import com.devfalah.ui.screen.savedPosts.savedPostsRoute
import com.devfalah.ui.screen.search.searchRoute
import com.devfalah.ui.screen.userInformation.editUserInformationRoute
import com.devfalah.ui.util.Constants


@Composable
fun ClubsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.screen_route
    ) {

        homeRoute(navController = navController)
        searchRoute(navController = navController)
        clubsRoute(navController = navController)
        notificationRoute(navController = navController)
        menuRoute(navController = navController)
        friendRequestRoute(navController = navController)
        profileRoute(navController = navController)
        createPostRoute(navController = navController)
        friendsRoute(navController = navController)
        savedPostsRoute(navController = navController)
        accountSettingsRoute(navController = navController)
        reportBugRoute(navController = navController)
        allSearchResultRoute(navController = navController)
        clubRequestsRoute(navController = navController)
        clubCreationRoute(navController = navController)
        clubsDetailsRoute(navController = navController)
        editUserInformationRoute(navController = navController)
        postDetailsRoute(navController = navController)
        editClubRoute(navController = navController)
        membersRoute(navController= navController)
    }
}

fun NavController.showingBack(): Boolean {
    return when (this.currentBackStackEntry?.destination?.route) {
        Screen.Home.screen_route,
        Screen.Clubs.screen_route,
        Screen.Search.screen_route,
        Screen.Notification.screen_route,
        Screen.Menu.screen_route -> false
        else -> true
    }
}

fun navigateToImageScreen(context: Context, imageUrl: String) {
    val intent = Intent(context, ImageActivity::class.java)
    intent.putExtra(Constants.IMAGE_URL_KEY, imageUrl)
    ContextCompat.startActivity(context, intent, null)
}