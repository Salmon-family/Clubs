package com.devfalah.ui.screen.createPost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.SetStatusBarColor
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.createPost.CreatePostViewModel
import com.devfalah.viewmodels.createPost.PostCreationUIState

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    SetStatusBarColor(LightBackgroundColor, darkIcons = true)
    val state by viewModel.uiState.collectAsState()

    CreatePostContent(
        state = state,
    )

}


@Composable
fun CreatePostContent(
    state: PostCreationUIState,
) {

}