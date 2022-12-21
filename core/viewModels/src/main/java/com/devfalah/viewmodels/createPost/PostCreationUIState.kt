package com.devfalah.viewmodels.createPost

data class PostCreationUIState(
    val id: Int = 0,
    val postContent: String = "",
    val privacy: Int = 2,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)