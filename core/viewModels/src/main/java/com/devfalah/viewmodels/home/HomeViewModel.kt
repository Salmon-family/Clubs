package com.devfalah.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetHomePostUseCase
import com.devfalah.usecases.GetUserIdUseCase
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
    val getUser: GetUserIdUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(id = getUser()) }
                getData()
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch { allPosts() }
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
                val homePosts = allPosts.loadData(uiState.value.id)
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        isEndOfPager = homePosts.isNotEmpty(),
                        posts = it.posts + homePosts.toUIState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isPagerLoading = false, isLoading = false, pagerError = t.message.toString()
                    )
                }
            }
        }
    }

}