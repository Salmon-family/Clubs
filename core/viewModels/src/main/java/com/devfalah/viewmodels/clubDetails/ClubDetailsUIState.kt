package com.devfalah.viewmodels.clubDetails

import com.devfalah.viewmodels.friends.FriendUIState
import com.devfalah.viewmodels.userProfile.PostUIState


data class ClubDetailsUiState(
    val detailsUiState: DetailsUIState = DetailsUIState(),
    val members: List<FriendUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val validateUserState: Boolean = false,
    val membersCount: Int = 0,
    val postCount: Int = 0,
    val isMember: Boolean = true,
    val requestExists: Boolean = false,
    val isEndOfPager: Boolean = false,
    val isLoading: Boolean = false,
    val isPagerLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val error: String = "",
    val pagerError: String = "",
)

data class DetailsUIState(
    val clubId: Int = 0,
    val ownerId: Int = 0,
    val isOwner: Boolean = false,
    val name: String = "",
    val description: String = "",
    val isOwnerOfClub: Boolean = false,
    val isClubPublic: Boolean = true
)

data class MembersUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)


fun ClubDetailsUiState.isPostVisible():Boolean{
   return isMember || detailsUiState.isOwner || detailsUiState.isClubPublic
}
