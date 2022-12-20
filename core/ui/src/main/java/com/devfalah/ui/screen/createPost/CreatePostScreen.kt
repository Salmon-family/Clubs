package com.devfalah.ui.screen.createPost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.createPost.CreatePostViewModel
import com.devfalah.viewmodels.createPost.PostCreationUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
        )
    }
    CreatePostContent(
        state = state,
    )

}


@Composable
fun CreatePostContent(
    state: PostCreationUIState,
) {

}