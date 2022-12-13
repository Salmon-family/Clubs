package com.devfalah.ui.screen.createPost

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.SetStatusBarColor
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.createPost.CreatePostViewModel

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    SetStatusBarColor(LightBackgroundColor, darkIcons = true)
    val state by viewModel.uiState.collectAsState()

    Text(text = "Create Post", fontSize = 24.sp)

}