package com.devfalah.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.viewmodels.home.HomeViewModel
import com.devfalah.viewmodels.userProfile.UserUIState


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HomeContent()
}

@Composable
fun HomeContent(
//    state: UserUIState,

    ) {

}
