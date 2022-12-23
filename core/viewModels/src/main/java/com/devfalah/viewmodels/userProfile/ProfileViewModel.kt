package com.devfalah.viewmodels.userProfile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.*
import com.devfalah.usecases.util.Constants.HOME_GROUP_ID
import com.devfalah.viewmodels.friends.toFriendsUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toUIState
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
    val likeUseCase: SetLikeUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
    val changeProfileImageUseCase: ChangeProfileImageUseCase,
    val deletePostUseCase: DeletePostUseCase,
    val getUser: GetUserIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ProfileArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserID()
    }

    fun getData() {
        getUserDetails(uiState.value.id, args.ownerId)
        getProfilePost(uiState.value.id, args.ownerId)
        getUserFriends(args.ownerId)
    }

    private fun getUserID() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(id = getUser()) }
                getData()
            } catch (t: Throwable) {
                _uiState.update { it.copy(majorError = t.message.toString()) }
            }
        }
    }

    private fun getUserDetails(userID: Int, profileOwnerID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, majorError = "") }
            try {
                val userDetails =
                    getUserAccountDetails(userId = userID, profileOwnerId = profileOwnerID)
                _uiState.update {
                    it.copy(
                        loading = false,
                        isMyProfile = userID == profileOwnerID,
                        userDetails = userDetails.toUIState(),
                    )
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

    private fun getProfilePost(userID: Int, profileOwnerID: Int) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        posts = getProfilePostUseCase(userID, profileOwnerID).toUIState(
                            HOME_GROUP_ID
                        )
                    )
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
                    postID = post.postId, userId = uiState.value.id,
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
                val success = addFriendUseCase(uiState.value.id, args.ownerId)
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

    fun swipeToRefresh(type: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(loading = true) }
                val posts = getProfilePostUseCase.loadMore(uiState.value.id, args.ownerId)
                _uiState.update {
                    it.copy(
                        loading = false,
                        posts = (it.posts + posts.toUIState(HOME_GROUP_ID)),
                        isEndOfPager = posts.isEmpty()
                    )
                }
            } catch (t: Throwable) {
                Log.e("TESTTEST", t.message.toString())
                _uiState.update { it.copy(loading = false, minorError = t.message.toString()) }
            }
        }
    }

    fun onClickPostSetting(post: PostUIState) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(loading = true) }
                if (deletePostUseCase(_uiState.value.userDetails.userID, post.postId)) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                    }
                }
            } catch (t: Throwable) {
                Log.e("Test", t.message.toString())
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

}