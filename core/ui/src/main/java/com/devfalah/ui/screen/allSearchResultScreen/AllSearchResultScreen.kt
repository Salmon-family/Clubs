package com.devfalah.ui.screen.allSearchResultScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.clubsDetail.navigateToClubDetails
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.viewmodels.allSearchResult.AllSearchResultUIState
import com.devfalah.viewmodels.allSearchResult.AllSearchResultViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun AllSearchResultScreenScreen(
    navController: NavController,
    viewModel: AllSearchResultViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    AllSearchResultScreenContent(
        state = state,
        onBackClick = { navController.popBackStack() },
        onRetry = viewModel::getData,
        onClubSelected = { navController.navigateToClubDetails(it) },
        OnUserClick = { navController.navigateToProfile(it) },
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@Composable
fun AllSearchResultScreenContent(
    state: AllSearchResultUIState,
    onBackClick: () -> Unit,
    onClubSelected: (Int) -> Unit,
    OnUserClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Column (modifier = Modifier.fillMaxSize()){
        AppBar(
            title = state.title,
            onBackButton = onBackClick,
        )

        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            Loading()
        } else if (state.users.isEmpty() && state.clubs.isEmpty()) {
            ErrorEmpty()
        } else {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (state.isClub) {
                    items(
                        items = state.clubs,
                        key = { "${it.id} ${it.title}" }
                    ) { club ->
                        ClubItem(state = club, onClubSelected = onClubSelected)
                    }
                } else {
                    items(
                        items = state.users,
                        key = { it.id }
                    ) { user ->
                        FriendItem(state = user, onOpenProfileClick = OnUserClick)
                    }
                }
            }
        }
    }
}
