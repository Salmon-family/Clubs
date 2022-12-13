package com.devfalah.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devfalah.ui.screen.clubs.clubsRoute
import com.devfalah.ui.screen.friendrequest.friendRequestRoute
import com.devfalah.ui.screen.home.homeRoute
import com.devfalah.ui.screen.menu.menuRoute
import com.devfalah.ui.screen.notification.notificationRoute
import com.devfalah.ui.screen.postDetails.postDetailsRoute
import com.devfalah.ui.screen.profile.profileRoute
import com.devfalah.ui.screen.search.searchRoute

const val Profile = "ProfileScreen"
const val FriendRequestRoute = "FriendRequest"
const val PostDetailsRoute = "postDetails"


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
        profileRoute(navController= navController)
        postDetailsRoute(navController = navController)
    }
}