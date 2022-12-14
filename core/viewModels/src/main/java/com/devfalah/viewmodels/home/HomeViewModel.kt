package com.devfalah.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetHomePostUseCase
import com.devfalah.usecases.SetFavoritePostUseCase
import com.devfalah.usecases.SetLikeUseCase
import com.devfalah.viewmodels.Constants.FIRST_TIME
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
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
    val favoritePostUseCase: SetFavoritePostUseCase,

    ) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()
    val userId = 6

    init {
        viewModelScope.launch { allPosts(userId) }
        swipeToRefresh(FIRST_TIME)
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
                _uiState.update { it.copy(error = t.message.toString()) }
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

    fun swipeToRefresh(type: Int) {
        viewModelScope.launch {
            if (type == FIRST_TIME) {
                _uiState.update { it.copy(isLoading = true) }
            } else {
                _uiState.update { it.copy(isPagerLoading = true) }
            }
            try {
                allPosts.loadData(userId, type).collect { posts ->
                    _uiState.update {
                        it.copy(
                            isPagerLoading = false,
                            isLoading = false,
                            posts = it.posts + posts.toUIState()
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        pagerError = t.message.toString()
                    )
                }
            }
        }
    }

}