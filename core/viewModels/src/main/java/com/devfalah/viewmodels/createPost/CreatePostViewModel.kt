package com.devfalah.viewmodels.createPost

import android.graphics.BitmapFactory
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
import java.io.File
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

    fun onClickSelectImage(file: File) {
        _uiState.update {
            it.copy(
                imageFile = file,
                imageBitmap = BitmapFactory.decodeFile(file.absolutePath)
            )
        }
    }

    fun onClickPost() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val post = createThreadUseCase(
                    userId = uiState.value.id,
                    postContent = uiState.value.postContent,
                    privacy = uiState.value.privacy,
                    imageFile = uiState.value.imageFile
                )
                Log.e("TEST", post.toString())
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }

            } catch (t: Throwable) {
                Log.e("Test", t.message.toString())
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }
}