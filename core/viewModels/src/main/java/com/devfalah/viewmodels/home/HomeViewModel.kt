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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
            getHomeThreads().collect { posts ->
                _uiState.update { it.copy(posts = posts.toUIState(), isLoading = false) }
            }
        }
    }


    fun getMorePosts() {
        _uiState.update { it.copy(isPagerLoading = true, pagerError = "") }
        getHomeThreads(SCROLL_DOWN)
    }

    fun updateHome() {
        _uiState.update { it.copy(isLoading = true, pagerError = "") }
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

    private var likeJob: Job? = null

    fun onClickLike(post: PostUIState) {
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

        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            try {
                delay(1500)
                likeUseCase(
                    postID = post.postId,
                    isLiked = post.isLikedByUser
                )
            } catch (t: Throwable) {
                //_uiState.update { it.copy(error = t.message.toString()) }
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

    fun onDeletePost(post: PostUIState) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (deletePostUseCase(post.postId)) {
                    _uiState.update {
                        it.copy(posts = _uiState.value.posts.filterNot { it.postId == post.postId })
                    }
                }
            } catch (t: Throwable) {
            }
        }
    }

}