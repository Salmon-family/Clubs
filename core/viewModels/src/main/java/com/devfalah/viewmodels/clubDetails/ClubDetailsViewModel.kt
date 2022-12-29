package com.devfalah.viewmodels.clubDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.*
import com.devfalah.usecases.posts.DeletePostUseCase
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
import com.devfalah.viewmodels.clubDetails.mapper.toUIState
import com.devfalah.viewmodels.clubDetails.mapper.toUserUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import com.devfalah.viewmodels.util.Constants.PRIVATE_PRIVACY
import com.devfalah.viewmodels.util.Constants.PUBLIC_PRIVACY
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {

    private val args = ClubDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ClubDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                getGroupWallUseCase(args.groupId)
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.message.toString()) }
            }
        }
        getData()
    }

    fun getData() {
        getClubDetails()
        getMembers()
        swipeToRefresh()
    }

    fun swipeToRefresh() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isPagerLoading = true, pagerError = "") }
                val posts = getGroupWallUseCase.loadMore(args.groupId)
                    .toUIState(args.groupId, uiState.value.name)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPagerLoading = false,
                        posts = (it.posts + posts),
                        isEndOfPager = (posts.isEmpty() || posts.size < MAX_PAGE_ITEM)
                    )
                }
                getPostCount()
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPagerLoading = false,
                        pagerError = t.message.toString()
                    )
                }
            }
        }
    }

    private fun getClubDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val clubDetails = getClubDetailsUseCase(groupID = args.groupId)
                _uiState.update {
                    it.copy(
                        clubId = clubDetails.id,
                        ownerId = clubDetails.ownerId,
                        name = clubDetails.name,
                        membership = clubDetails.privacy.toInt(),
                        description = clubDetails.description,
                        privacy = getPrivacy(clubDetails.privacy),
                        requestExists = clubDetails.requestExists,
                        isMember = clubDetails.isMember,
                        isOwner = clubDetails.isOwner,
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
        viewModelScope.launch {
            try {
                val postCount = getGroupWallUseCase.getPostsCount()
                _uiState.update { it.copy(postCount = postCount) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.message.toString()) }
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
                val members = getClubMembersUseCase(uiState.value.ownerId,args.groupId).toUserUIState()
                _uiState.update { it.copy(membersCount = members.size,members = members) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(pagerError = t.message.toString()) }
            }
        }
    }

    fun onClickLike(post: PostUIState) {
        viewModelScope.launch {
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
        viewModelScope.launch {
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
        viewModelScope.launch {
            try {
                joinClubUseCase(clubId = args.groupId)
                _uiState.update { it.copy(requestExists = true) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun unJoinClubs() {
        viewModelScope.launch {
            try {
                unJoinClubUseCase(clubId = args.groupId)
                _uiState.update { it.copy(requestExists = false) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    fun declineRequestOfClub() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isMember = false, requestExists = false) }
            } catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
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

}