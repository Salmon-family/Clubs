package com.devfalah.viewmodels.userProfile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.*
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toFriendsUIState
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


/**
 * Need To Do:
 * add friend status (pending ..)
 * get album cover photo.
 * save post
 * block or unfriend user.
 * create post
 * change cover or profile photo.
 * display youtube view.

 *
 * navigate to :
 * image in full screen.
 * comments
 * messages
 * external URL
 * selected/all albums
 * selected/all friends
 *
 * */

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val getUserAccountDetails: GetUserAccountDetailsUseCase,
    val getUserFriendsUseCase: GetUserFriendsUseCase,
    val getProfilePostUseCase: GetProfilePostsUseCase,
    val addFriendUseCase: AddFriendUseCase,
    val likeUseCase: SetLikeUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
    val changeProfileImageUseCase: ChangeProfileImageUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val ownerID = checkNotNull(savedStateHandle["profileId"]).toString().toInt()

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    private val userId = 6

    init {
        getData()
    }

    fun getData() {
        getUserDetails(userId, ownerID)
        getUserFriends(ownerID)
        getProfilePost(userId, ownerID)
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
                _uiState.update { it.copy(friends = getUserFriendsUseCase(profileOwnerID).toFriendsUIState()) }
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
                        posts = getProfilePostUseCase(userID, profileOwnerID).toUIState()
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
                    postID = post.postId, userId = userId,
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
            } catch (t: Throwable) {
                t.message.toString()
            }
        }
    }

    fun onClickAddFriend() {
        viewModelScope.launch {
            try {
                val success = addFriendUseCase(userId, ownerID)
                if (success) {
                    _uiState.update { it.copy(userDetails = it.userDetails.copy(areFriends = true)) }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickChangeImage(file: File) {
        //should display dialog chose from album or select from yours.
        viewModelScope.launch {
            try {
                val updatedUser = changeProfileImageUseCase(userId = ownerID, file)
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
                val posts = getProfilePostUseCase.loadMore(userId, ownerID, type).toUIState()
                _uiState.update { it.copy(loading = false, posts = posts) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(loading = false, minorError = t.message.toString()) }
            }
        }
    }

    fun onClickPostSetting(post: PostUIState) {

    }


}