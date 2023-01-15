package com.devfalah.viewmodels.savedPosts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.user.GetUserFavoritePostsUseCase
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
import com.devfalah.viewmodels.userProfile.PostUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPostsViewModel @Inject constructor(
    val savedPosts: GetUserFavoritePostsUseCase,
    private val removeFavoritePost: SetFavoritePostUseCase,
    val likeUseCase: SetPostLikeUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SavedPostUIState())
    val uiState = _uiState.asStateFlow()

    private var likeJob: Job? = null

    init {
        viewModelScope.launch {
            savedPosts().collect { posts ->
                _uiState.update { it.copy(posts = posts.toSavedUIState()) }
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
                    publisherId = post.publisherId,
                    clubName = post.groupName,
                )
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    fun onClickRemoveSavedPost(post: PostUIState) {
        viewModelScope.launch {
            try {
                removeFavoritePost.deletePostLocally(post.postId)
                _uiState.update {
                    it.copy(posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

}