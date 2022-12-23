package com.devfalah.viewmodels.clubDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.*
import com.devfalah.viewmodels.Constants.PRIVATE_PRIVACY
import com.devfalah.viewmodels.Constants.PUBLIC_PRIVACY
import com.devfalah.viewmodels.clubDetails.mapper.toUIState
import com.devfalah.viewmodels.clubDetails.mapper.toUserUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubDetailsViewModel @Inject constructor(
    private val getUser: GetUserIdUseCase,
    private val getClubDetailsUseCase: GetClubDetailsUseCase,
    private val getClubMembersUseCase: GetClubMembersUseCase,
    private val getGroupWallUseCase: GetGroupWallUseCase,
    private val likeUseCase: SetLikeUseCase,
    private val getProfilePostUseCase: GetProfilePostsUseCase,
    private val favoritePostUseCase: SetFavoritePostUseCase,
    private val joinClubUseCase: JoinClubUseCase,
    private val unJoinClubUseCase: UnJoinClubUseCase,
    private val getClubDeclineUseCase: GetClubDeclineUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = ClubDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ClubDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(userId = getUser()) }
                getData()
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    fun getData() {
        checkPrivacyAndIMemberInClub()
        getClubDetails()
        getMemberCount()
        getPostCount()
        getMembers()
        getClubPost()
    }


    fun swipeToRefresh(type: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val posts =
                    getProfilePostUseCase.loadMore(uiState.value.clubId, _uiState.value.ownerId)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        posts = (it.posts + posts.toUIState()),
                        isEndOfPager = posts.isEmpty()
                    )
                }
            } catch (t: Throwable) {
                Log.e("TESTTEST", t.message.toString())
                _uiState.update { it.copy(isLoading = false, errorMessage = t.message.toString()) }
            }
        }
    }


    private fun getClubDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = "") }
            try {
                val clubDetails =
                    getClubDetailsUseCase(userID = args.userID, groupID = args.groupId)

                _uiState.update {
                    it.copy(
                        clubId = clubDetails.id,
                        ownerId = clubDetails.ownerId,
                        name = clubDetails.name,
                        description = clubDetails.description,
                        privacy = getPrivacy(clubDetails.privacy),
                        requestExists = clubDetails.requestExists,
                        isMember = clubDetails.isMember,
                        isLoading = false,
                        isSuccessful = true
                    )
                }

            } catch (throwable: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = false,
                        errorMessage = throwable.message.toString()
                    )
                }
            }
        }
    }

    private fun getMemberCount() {
        viewModelScope.launch {
            try {
                val memberCount = getClubMembersUseCase(_uiState.value.clubId).size
                _uiState.update { it.copy(membersCount = memberCount) }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false, isSuccessful = false, errorMessage = t.message.toString()
                    )
                }
            }
        }

    }

    private fun getPostCount() {
        viewModelScope.launch {
            try {
                val postCount =
                    getGroupWallUseCase(_uiState.value.userId, _uiState.value.clubId).count
                _uiState.update { it.copy(postCount = postCount) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    private fun getPrivacy(value: String): String {
        return when (value) {
            "1" -> PRIVATE_PRIVACY
            else -> PUBLIC_PRIVACY
        }
    }

    private fun getMembers() {
        viewModelScope.launch {
            try {
                val members = getClubMembersUseCase(_uiState.value.clubId).toUserUIState()
                _uiState.update { it.copy(members = members) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    private fun getClubPost() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        posts = getGroupWallUseCase(
                            _uiState.value.userId,
                            _uiState.value.clubId
                        ).post.toUIState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    fun onClickLike(post: PostUIState) {
        viewModelScope.launch {
            try {
                val totalLikes = likeUseCase(
                    postID = post.postId, userId = uiState.value.userId,
                    isLiked = post.isLikedByUser
                )
                val updatedPost = post.copy(
                    isLikedByUser = !post.isLikedByUser, totalLikes = totalLikes
                )
                _uiState.update {
                    it.copy(posts = uiState.value.posts.map {
                        if (it.postId == post.postId) {
                            updatedPost
                        } else {
                            it
                        }
                    })
                }
            } catch (t: Throwable) {
                Log.e("Test Test Test", t.message.toString())
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    fun onClickSave(post: PostUIState) {
        viewModelScope.launch {
            try {
                favoritePostUseCase(post.toEntity())
                _uiState.update {
                    it.copy(
                        posts = _uiState.value.posts
                            .map {
                                if (it.postId == post.postId) {
                                    it.copy(isSaved = true)
                                } else {
                                    it
                                }
                            }
                    )
                }
            } catch (t: Throwable) {
                t.message.toString()
            }
        }
    }


    fun joinClubs() {
        viewModelScope.launch {
            try {
                joinClubUseCase(clubId = _uiState.value.clubId, userId = _uiState.value.userId)
                _uiState.update { it.copy(requestExists = true) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun unJoinClubs() {
        viewModelScope.launch {
            try {
                unJoinClubUseCase(
                    clubId = _uiState.value.clubId,
                    userId = _uiState.value.userId
                )
                _uiState.update { it.copy(requestExists = false) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun declineRequestOfClub() {
        viewModelScope.launch {
            try {
                getClubDeclineUseCase(
                    clubId = _uiState.value.clubId,
                    memberId = _uiState.value.userId,
                    userId = _uiState.value.ownerId
                )
                _uiState.update { it.copy(isMember = false, requestExists = false) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun isMyPost(postId: Int) = postId == _uiState.value.ownerId

    private fun checkPrivacyAndIMemberInClub() {
        if (_uiState.value.privacy != PUBLIC_PRIVACY && !_uiState.value.isMember) {
            _uiState.update { it.copy(validateUserState = true) }
        } else {
            _uiState.update { it.copy(validateUserState = false) }
        }
    }
}