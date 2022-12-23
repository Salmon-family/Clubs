package com.devfalah.viewmodels.clubDetails

import com.devfalah.entities.Post
import com.devfalah.entities.User
import com.devfalah.viewmodels.userProfile.PostUIState


data class ClubDetailsUiState(
    val clubId: Int = 0,
    val userId: Int = 0,
    val ownerId: Int = 0,
    val name: String = "",
    val description: String = "",
    val privacy: String = "",
    val membersCount: Int = 0,
    val postCount: Int = 0,
    val members: List<MembersUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val validateUserState: Boolean = false,
    val requestExists: Boolean = false,
    val isEndOfPager: Boolean = false,
    val isMember: Boolean = true,
    val isLoading: Boolean = false,
    val isPagerLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String = "",
    val pagerError: String = "",
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