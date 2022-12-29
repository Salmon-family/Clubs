package com.devfalah.viewmodels.clubDetails

import com.devfalah.viewmodels.userProfile.PostUIState


data class ClubDetailsUiState(
    val clubId: Int = 0,
    val ownerId: Int = 0,
    val isOwner: Boolean = false,
    val name: String = "",
    val description: String = "",
    val privacy: String = "",
    val membership: Int = 2,
    val membersCount: Int = 0,
    val postCount: Int = 0,
    val members: List<MembersUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val validateUserState: Boolean = false,
    val requestExists: Boolean = false,
    val isEndOfPager: Boolean = false,
    val isMember: Boolean = true,
    val isOwnerOfClub: Boolean = false,
    val isLoading: Boolean = false,
    val isPagerLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val error: String = "",
    val pagerError: String = "",
)

data class MembersUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)
