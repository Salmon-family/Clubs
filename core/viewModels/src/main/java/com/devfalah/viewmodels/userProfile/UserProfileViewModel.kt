package com.devfalah.viewmodels.userProfile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    val getUserAccountDetails: GetUserAccountDetailsUseCase,
    val getUserAlbumsUseCase: GetUserAlbumsUseCase,
    val getUserFriendsUseCase: GetUserFriendsUseCase,
    val getProfilePostUseCase: GetProfilePostsUseCase,
    val likeUseCase: SetLikeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    private val userId = 6

    init {
        getUserDetails(userId)
        getUserAlbums(userId)
        getUserFriends(userId)
        getProfilePost(userId)
    }

    private fun getUserDetails(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                _uiState.update {
                    it.copy(
                        loading = false,
                        userDetails = getUserAccountDetails(userID).toUIState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(loading = false, error = t.message.toString()) }
            }
        }
    }

    private fun getUserAlbums(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(albums = getUserAlbumsUseCase(userID).map { it.toUIState() }) }
        }
    }

    private fun getUserFriends(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(friends = getUserFriendsUseCase(userID).map { it.toUIState() }) }
        }
    }

    private fun getProfilePost(userID: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(posts = getProfilePostUseCase(userID).map { it.toUIState() }) }
        }
    }

    fun onClickLike(post: PostUIState) {
        viewModelScope.launch {
            try {
                val updatedPost = post.copy(
                    isLikedByUser = !post.isLikedByUser,
                    totalLikes = likeUseCase(
                        postID = post.postId,
                        userId = userId,
                        isLiked = post.isLikedByUser
                    )
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
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    fun onClickSave(post: PostUIState) {
        Log.e("Test", "Save $post")
    }

    fun onClickComment(post: PostUIState) {
        Log.e("Test", "Comment $post")
    }
}