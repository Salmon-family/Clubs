package com.devfalah.viewmodels.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetHomePostUseCase
import com.devfalah.usecases.SetLikeUseCase
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val likeUseCase: SetLikeUseCase,
    val allPosts: GetHomePostUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()
    val userId = 6


    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val posts = allPosts(userId)
                _uiState.update { it.copy(isLoading = false, posts = posts.toUIState()) }
            } catch (t: Throwable) {

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
        Log.e("Test", "Save $post")
    }

}