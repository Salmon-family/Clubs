package com.devfalah.viewmodels.clubDetails

import com.devfalah.entities.Post
import com.devfalah.entities.User
import com.devfalah.viewmodels.userProfile.PostUIState


data class ClubDetailsUiState(
    val name: String = "",
    val description: String = "",
    val privacy: String = "",
    val membersCount: Int = 0,
    val postCount: Int = 0,
    val members: List<MembersUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val isMember: Boolean = true,
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String = ""
)

data class MembersUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)

data class GroupWallPostUIState(
    val friends: List<Any>,
    val location: String,
    val post: Post,
    val text: String,
    val user: User
)