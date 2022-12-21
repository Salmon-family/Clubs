package com.devfalah.viewmodels.createPost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.CreateThreadUseCase
import com.devfalah.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    val createThreadUseCase: CreateThreadUseCase,
    val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostCreationUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(id = getUserIdUseCase()) }
            } catch (t: Throwable) {

            }
        }
    }

    fun onPrivacyChange(privacy: Int) {
        _uiState.update { it.copy(privacy = (privacy + 2)) }
    }

    fun onPostChange(post: String) {
        _uiState.update { it.copy(postContent = post) }
    }

    fun onClickPost() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val post = createThreadUseCase(
                    userId = uiState.value.id,
                    postContent = uiState.value.postContent,
                    privacy = uiState.value.privacy
                )
                Log.e("TEST", post.toString())
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }

            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }
}