package com.devfalah.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.viewmodels.home.HomeViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HomeContent(
        onClickFriends = { navController.navigate(route = Screen.FriendRequestRoute.screen_route) },
        onClickProfile = { navController.navigate(Screen.Profile.screen_route) })
}

@Composable
fun HomeContent(
    onClickFriends: () -> Unit,
    onClickProfile: () -> Unit
) {
    Column() {
        Text(text = "Home", fontSize = 24.sp)

        Button(onClick = { onClickFriends() }) {
            Text(text = "Friend Request")
        }

        Button(onClick = { onClickProfile() }) {
            Text(text = "Profile")
        }
    }
}
