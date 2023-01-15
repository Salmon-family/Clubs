package com.devfalah.viewmodels.home

import com.devfalah.viewmodels.userProfile.PostUIState

data class HomeUIState(
    val posts: List<PostUIState> = emptyList(),
    val isLoading: Boolean = false,
    val isPagerLoading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val isPostDeleted: Boolean = false,
    val error: Int = -1,
    val pagerError: String = "",
)

