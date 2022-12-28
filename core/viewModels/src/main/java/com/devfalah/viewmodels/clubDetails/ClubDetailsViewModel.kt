package com.devfalah.viewmodels.clubDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.*
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
import com.devfalah.viewmodels.clubDetails.mapper.toClubDetailsUIState
import com.devfalah.viewmodels.clubDetails.mapper.toUIState
import com.devfalah.viewmodels.clubDetails.mapper.toUserUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubDetailsViewModel @Inject constructor(
    private val getClubDetailsUseCase: GetClubDetailsUseCase,
    private val getClubMembersUseCase: GetClubMembersUseCase,
    private val getGroupWallUseCase: GetGroupWallUseCase,
    private val likeUseCase: SetPostLikeUseCase,
    private val favoritePostUseCase: SetFavoritePostUseCase,
    private val joinClubUseCase: JoinClubUseCase,
    private val unJoinClubUseCase: UnJoinClubUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var gettingDetailsClubsJob: Job? = null

    private val args = ClubDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ClubDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {

        makeRequest(
            onSuccess = {
                viewModelScope.launch {
                    getGroupWallUseCase(args.groupId)
                }
            },
            onFailure = { throwable ->
                _uiState.update { it.copy(pagerError = throwable.message.toString()) }
            }
        )
        getDetailsOfClubs()
    }

    fun getDetailsOfClubs() {
        getClubDetails()
        getMembers()
        swipeToRefresh()
    }

    fun swipeToRefresh() {
        makeRequest(
            onSuccess = {
                viewModelScope.launch {
                    _uiState.update { it.copy(isPagerLoading = true, pagerError = "") }
                    val posts = getGroupWallUseCase.loadMore(args.groupId)
                        .toUIState(args.groupId, uiState.value.detailsUiState.name)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isPagerLoading = false,
                            posts = (it.posts + posts),
                            isEndOfPager = (posts.isEmpty() || posts.size < MAX_PAGE_ITEM)
                        )
                    }
                    getPostCount()
                }
            },
            onFailure = { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPagerLoading = false,
                        pagerError = throwable.message.toString()
                    )
                }
            }
        )
    }

    private fun getClubDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val clubDetails =
                    getClubDetailsUseCase(groupID = args.groupId)
                _uiState.update {
                    it.copy(
                        detailsUiState = clubDetails.toClubDetailsUIState(),
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
                        error = throwable.message.toString()
                    )
                }
            }
        }
    }

    private fun getPostCount() {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                val postCount = getGroupWallUseCase.getPostsCount()
                _uiState.update { it.copy(postCount = postCount) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.message.toString()) }
            }
        }
    }

    private fun getMembers() {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                val members =
                    getClubMembersUseCase(
                        uiState.value.detailsUiState.ownerId,
                        args.groupId
                    ).toUserUIState()
                _uiState.update { it.copy(membersCount = members.size, members = members) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.message.toString()) }
            }
        }
    }

    fun onClickLike(post: PostUIState) {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                val totalLikes = likeUseCase(postID = post.postId, isLiked = post.isLikedByUser)
                val updatedPost = post.copy(
                    isLikedByUser = !post.isLikedByUser, totalLikes = totalLikes
                )
                _uiState.update {
                    it.copy(posts = _uiState.value.posts.map {
                        if (it.postId == post.postId) {
                            updatedPost
                        } else {
                            it
                        }
                    })
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.message.toString()) }
            }
        }
    }

    fun onClickSave(post: PostUIState) {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                favoritePostUseCase(post.toEntity())
                _uiState.update {
                    it.copy(
                        posts = _uiState.value.posts
                            .map {
                                if (it.postId == post.postId) {
                                    it.copy(isSaved = !post.isSaved)
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
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                joinClubUseCase(clubId = args.groupId)
                _uiState.update { it.copy(requestExists = true) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun unJoinClubs() {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                unJoinClubUseCase(clubId = args.groupId)
                _uiState.update { it.copy(requestExists = false) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun declineRequestOfClub() {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            try {
                _uiState.update { it.copy(isMember = false, requestExists = false) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    private fun makeRequest(onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        try {
            onSuccess()
        } catch (throwable: Throwable) {
            onFailure(throwable)
        }
    }

}