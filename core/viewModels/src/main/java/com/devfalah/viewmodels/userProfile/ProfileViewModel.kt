package com.devfalah.viewmodels.userProfile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.friend.AddFriendUseCase
import com.devfalah.usecases.friend.GetUserFriendsUseCase
import com.devfalah.usecases.friend.RemoveFriendRequestUseCase
import com.devfalah.usecases.posts.DeletePostUseCase
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
import com.devfalah.usecases.user.ChangeProfileImageUseCase
import com.devfalah.usecases.user.GetProfilePostsUseCase
import com.devfalah.usecases.user.GetUserAccountDetailsUseCase
import com.devfalah.viewmodels.friends.toFriendsUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val getUserAccountDetails: GetUserAccountDetailsUseCase,
    val getUserFriendsUseCase: GetUserFriendsUseCase,
    val getProfilePostUseCase: GetProfilePostsUseCase,
    val addFriendUseCase: AddFriendUseCase,
    val likeUseCase: SetPostLikeUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
    val changeProfileImageUseCase: ChangeProfileImageUseCase,
    val deletePostUseCase: DeletePostUseCase,
    val removeFriendUseCase: RemoveFriendRequestUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ProfileArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    private var likeJob: Job? = null

    init {
        getUserDetails(args.ownerId)
        getProfilePost(args.ownerId)
        getUserFriends(args.ownerId)
    }

    fun retryGetProfileData() {
        getUserDetails(args.ownerId)
        getProfilePost(args.ownerId)
        getUserFriends(args.ownerId)
        getProfileThreads()
    }

    private fun getUserDetails(profileOwnerID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, majorError = "") }
            try {
                val userDetails = getUserAccountDetails(profileOwnerId = profileOwnerID)
                _uiState.update {
                    it.copy(loading = false, userDetails = userDetails.toUIState())
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(loading = false, majorError = t.message.toString()) }
            }
        }
    }

    private fun getUserFriends(profileOwnerID: Int) {
        viewModelScope.launch {
            try {
                val friends = getUserFriendsUseCase(profileOwnerID)
                _uiState.update {
                    it.copy(
                        friends = friends.friends.toFriendsUIState(), totalFriends = friends.total
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(loading = false, minorError = t.message.toString()) }
            }
        }
    }

    private fun getProfilePost(profileOwnerID: Int) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(posts = getProfilePostUseCase(profileOwnerID).toUIState())
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickLike(post: PostUIState) {
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            try {
                val updatedPost = post.copy(
                    isLikedByUser = !post.isLikedByUser,
                    totalLikes = if (post.isLikedByUser) post.totalLikes - 1 else post.totalLikes + 1
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
                delay(1000)
                likeUseCase(
                    postID = post.postId,
                    isLiked = post.isLikedByUser,
                    publisherId = post.publisherId
                )
            } catch (t: Throwable) {
                //_uiState.update { it.copy(minorError = t.message.toString()) }
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

    fun onClickAddFriend() {
        viewModelScope.launch {
            try {
                val success = addFriendUseCase(args.ownerId, _uiState.value.userDetails.token)
                if (success) {
                    _uiState.update {
                        it.copy(
                            userDetails = it.userDetails.copy(
                                areFriends = false,
                                isRequestSend = true
                            )
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickChangeImage(file: File) {
        viewModelScope.launch {
            try {
                val updatedUser = changeProfileImageUseCase(userId = args.ownerId, file)
                _uiState.update { it.copy(userDetails = it.userDetails.copy(profilePicture = updatedUser.profileUrl)) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(loading = false, majorError = e.message.toString()) }
            }
        }
    }

    fun getProfileThreads() {
        getThreads()
    }

    fun onClickPostSetting(post: PostUIState) {
        viewModelScope.launch {
            try {
                if (deletePostUseCase(post.postId)) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun refreshProfileThreads() {
        getThreads(isRefresh = true)
    }

    private fun getThreads(isRefresh: Boolean = false) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isPagerLoading = true, minorError = "") }
                val posts = getProfilePostUseCase.loadMore(args.ownerId, isRefresh)
                _uiState.update {
                    it.copy(
                        loading = false,
                        posts = if (isRefresh) {
                            posts.toUIState()
                        } else {
                            it.posts + posts.toUIState()
                        },
                        isPagerLoading = false,
                        isEndOfPager = (posts.isEmpty() || posts.size < MAX_PAGE_ITEM)
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(isPagerLoading = false, minorError = t.message.toString())
                }
            }
        }
    }

    fun onRemoveFriend() {
        viewModelScope.launch {
            try {
                if (removeFriendUseCase(uiState.value.userDetails.userID)) {
                    _uiState.update {
                        it.copy(
                            userDetails = it.userDetails.copy(areFriends = false, isRequestSend = false)
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }
}