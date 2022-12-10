package com.devfalah.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.home.HomeUIState
import com.devfalah.viewmodels.home.HomeViewModel
import com.devfalah.viewmodels.userProfile.PostUIState


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HomeContent(
        state = state,
        onClickLike = viewModel::onClickLike,
        // should navigate to post screen details.
        onClickComment = {navController.navigate(Screen.CreatePost.screen_route)},
        onClickSave = viewModel::onClickSave,
        onCreatePost = { navController.navigate(Screen.CreatePost.screen_route) }
    )
}

@Composable
fun HomeContent(
    state: HomeUIState,
    onCreatePost: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,

    ) {
    LazyColumn(
        modifier = Modifier
            .background(LightBackgroundColor)
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            PostCreatingSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                onCreatePost = onCreatePost,
                isMyProfile = true
            )
        }

        items(state.posts){
            PostItem(
                state = it,
                isMyProfile = true,
                isContentExpandable = true,
                onClickLike = { onClickLike(it) },
                onClickComment = { onClickComment(it) },
                onClickSave = { onClickSave(it) },
                onClickPostSetting = { /*onClickPostSetting(it)*/ }
            )
        }


    }
}
