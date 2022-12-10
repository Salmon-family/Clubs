package com.devfalah.viewmodels.home

import com.devfalah.viewmodels.userProfile.PostUIState

data class HomeUIState(
    val posts: List<PostUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val minorError: String = ""
)