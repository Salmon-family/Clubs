package com.devfalah.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetHomeThreadsUseCase
import com.devfalah.usecases.posts.DeletePostUseCase
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
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
    val likeUseCase: SetPostLikeUseCase,
    val getHomeThreads: GetHomeThreadsUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
    val deletePostUseCase: DeletePostUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        _uiState.update { it.copy(error = "", isLoading = true) }
        viewModelScope.launch {
            getHomeThreads()
            swipeToRefresh()
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

    fun swipeToRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isPagerLoading = true, pagerError = "") }
            try {
                val homePosts = getHomeThreads.loadData()
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        isEndOfPager = homePosts.isEmpty(),
                        posts = it.posts + homePosts.toUIState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        pagerError = if (_uiState.value.posts.isNotEmpty()) {
                            t.message.toString()
                        } else {
                            ""
                        },
                        error = if (_uiState.value.posts.isEmpty()) {
                            t.message.toString()
                        } else {
                            ""
                        }
                    )
                }
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