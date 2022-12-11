package com.devfalah.viewmodels.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetHomePostUseCase
import com.devfalah.usecases.SetLikeUseCase
import com.devfalah.viewmodels.Constants
import com.devfalah.viewmodels.Constants.FIRST_TIME
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
        viewModelScope.launch { allPosts(userId) }
        swipeToRefresh(Constants.FIRST_TIME)
    }

    fun onClickLike(post: PostUIState) {

    }

    fun onClickSave(post: PostUIState) {
        Log.e("Test", "Save $post")
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