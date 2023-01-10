package com.devfalah.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetHomeThreadsUseCase
import com.devfalah.usecases.posts.DeletePostUseCase
import com.devfalah.usecases.posts.SetFavoritePostUseCase
import com.devfalah.usecases.posts.SetPostLikeUseCase
import com.devfalah.usecases.util.Constants.SCROLL_DOWN
import com.devfalah.usecases.util.Constants.SCROLL_UP
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import com.devfalah.viewmodels.util.ErrorsType.DELETE_ERROR
import com.devfalah.viewmodels.util.ErrorsType.HOME_ERROR
import com.devfalah.viewmodels.util.ErrorsType.LIKE_ERROR
import com.devfalah.viewmodels.util.ErrorsType.NO_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
        _uiState.update { it.copy(error = NO_ERROR, isLoading = true) }
        try {
            viewModelScope.launch {
                getHomeThreads().collect { posts ->
                    _uiState.update { it.copy(posts = posts.toUIState(), isLoading = false) }
                }
            }
        } catch (t: Throwable) {
            _uiState.update { it.copy(error = HOME_ERROR) }
        }
    }

    fun getMorePosts() {
        _uiState.update { it.copy(isPagerLoading = true, error = NO_ERROR, pagerError = "") }
        getHomeThreads(SCROLL_DOWN)
    }

    fun updateHome() {
        _uiState.update { it.copy(isLoading = true, error = NO_ERROR, pagerError = "") }
        getHomeThreads(SCROLL_UP)
    }

    private fun getHomeThreads(scrollDirection: Int) {
        viewModelScope.launch {
            try {
                val loadMore = getHomeThreads.loadData(scrollDirection)
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        isEndOfPager = !loadMore,
                    )
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        pagerError = t.message.toString(),
                    )
                }
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
                _uiState.update { it.copy(error = LIKE_ERROR) }
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
                                if (it.postId == post.postId) { it.copy(isSaved = !post.isSaved) }
                                else { it }
                            }
                    )
                }
            } catch (t: Throwable) {
                t.message.toString()
            }
        }
    }

    fun onDeletePost(post: PostUIState) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (deletePostUseCase(post.postId)) {
                    _uiState.update {
                        it.copy(posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = DELETE_ERROR) }
            }
        }
    }

}