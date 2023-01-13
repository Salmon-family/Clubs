package com.devfalah.viewmodels.clubDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.*
import com.devfalah.usecases.posts.DeletePostUseCase
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
import com.devfalah.viewmodels.BaseViewModel
import com.devfalah.viewmodels.clubDetails.mapper.toClubDetailsUIState
import com.devfalah.viewmodels.clubDetails.mapper.toUIState
import com.devfalah.viewmodels.friends.toFriendsUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    val deletePostUseCase: DeletePostUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    var gettingDetailsClubsJob: Job? = null

    private val args = ClubDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ClubDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private var likeJob: Job? = null

    init {
        viewModelScope.launch {
            makeRequest(
                onSuccess = { getGroupWallUseCase(args.groupId) },
                onFailure = ::onFailure
            )
        }
    }

    fun getDetailsOfClubs() {
        getClubDetails()
        getClubThreads()
    }

    fun getClubThreads(isRestart: Boolean = false) {
        viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    _uiState.update { it.copy(isPagerLoading = true, pagerError = "") }
                    val posts = getGroupWallUseCase.loadMore(args.groupId, isRestart)
                        .toUIState(args.groupId, uiState.value.detailsUiState.name)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isPagerLoading = false,
                            posts = if (isRestart) { posts } else { it.posts + posts },
                            isEndOfPager = (posts.isEmpty() || posts.size < MAX_PAGE_ITEM),
                            postCount = getGroupWallUseCase.getPostsCount()
                        )
                    }
                },
                onFailure = ::onFailure
            )
        }
    }

    fun refreshClub() {
        getClubDetails()
        getClubThreads(true)
    }

    private fun getClubDetails() {
        viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    _uiState.update { it.copy(error = "") }
                    val clubDetails = getClubDetailsUseCase(groupID = args.groupId)
                    _uiState.update {
                        it.copy(
                            detailsUiState = clubDetails.toClubDetailsUIState(),
                            requestExists = clubDetails.requestExists,
                            isMember = clubDetails.isMember,
                            isVisibility = true,
                            isLoading = false,
                            isSuccessful = true
                        )
                    }
                    getMembers()
                },
                onFailure = ::onFailure
            )
        }
    }

    private fun getMembers() {
        gettingDetailsClubsJob = viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    val members = getClubMembersUseCase(uiState.value.detailsUiState.ownerId, args.groupId)
                    _uiState.update {
                        it.copy(
                            membersCount = getClubMembersUseCase.getTotalMembers(),
                            members = members.toFriendsUIState()
                        )
                    }
                },
                onFailure = ::onFailure
            )
        }
    }

    fun onClickLike(post: PostUIState) {
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    val updatedPost = post.copy(
                        isLikedByUser = !post.isLikedByUser,
                        totalLikes = if (post.isLikedByUser) post.totalLikes - 1 else post.totalLikes + 1
                    )
                    _uiState.update {
                        it.copy(posts = _uiState.value.posts.map { postUiState ->
                            if (postUiState.postId == post.postId) updatedPost else postUiState
                        })
                    }
                    delay(1000)
                    likeUseCase(postID = post.postId, isLiked = post.isLikedByUser)
                },
                onFailure = {}
            )
        }
    }

    fun onClickSave(post: PostUIState) {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    favoritePostUseCase(post.toEntity())
                    _uiState.update {
                        it.copy(posts = _uiState.value.posts.map { postUiState ->
                            if (postUiState.postId == post.postId) {
                                postUiState.copy(isSaved = !post.isSaved)
                            } else {
                                postUiState
                            }
                        })
                    }
                },
                onFailure = ::onFailure
            )
        }
    }

    fun joinClubs() {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    joinClubUseCase(clubId = args.groupId)
                    _uiState.update { it.copy(requestExists = true) }
                },
                onFailure = ::onFailure
            )
        }
    }

    fun unJoinClubs() {
        gettingDetailsClubsJob?.cancel()
        gettingDetailsClubsJob = viewModelScope.launch {
            makeRequest(
                onSuccess = {
                    unJoinClubUseCase(clubId = args.groupId)
                    _uiState.update { it.copy(requestExists = false) }
                },
                onFailure = ::onFailure
            )
        }
    }

    fun onDeletePost(post: PostUIState) {
        viewModelScope.launch {
            try {
                if (deletePostUseCase(post.postId)) {
                    _uiState.update {
                        it.copy(posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    private fun onFailure(throwable: Throwable) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isPagerLoading = false,
                isSuccessful = false,
                error = throwable.message.toString()
            )
        }
    }

}