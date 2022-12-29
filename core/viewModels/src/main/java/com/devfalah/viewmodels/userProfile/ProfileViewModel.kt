package com.devfalah.viewmodels.userProfile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.friend.AddFriendUseCase
import com.devfalah.usecases.friend.GetUserFriendsUseCase
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
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ProfileArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        getUserDetails(args.ownerId)
        getProfilePost(args.ownerId)
        getUserFriends(args.ownerId)
        swipeToRefresh()
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
        viewModelScope.launch {
            try {
                val totalLikes = likeUseCase(
                    postID = post.postId,
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
                _uiState.update { it.copy(minorError = t.message.toString()) }
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

    fun onClickAddFriend() {
        viewModelScope.launch {
            try {
                val success = addFriendUseCase(args.ownerId)
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

    fun swipeToRefresh() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isPagerLoading = true, minorError = "") }
                val posts = getProfilePostUseCase.loadMore(args.ownerId)
                _uiState.update {
                    it.copy(
                        loading = false,
                        posts = (it.posts + posts.toUIState()),
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

    fun onClickPostSetting(post: PostUIState) {
        viewModelScope.launch {
            try {
                if (deletePostUseCase(post.postId)) {
                    _uiState.update {
                        it.copy(posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

}