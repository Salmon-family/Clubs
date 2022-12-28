package com.devfalah.viewmodels.postCreation

import android.graphics.BitmapFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.CreateThreadUseCase
import com.devfalah.viewmodels.util.Constants.HOME_CLUB_ID
import com.devfalah.viewmodels.util.Constants.PROFILE_CLUB_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostCreationViewModel @Inject constructor(
    val createThreadUseCase: CreateThreadUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = PostCreationArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(PostCreationUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    clubId = args.clubId,
                    isClub = args.clubId != HOME_CLUB_ID && args.clubId != PROFILE_CLUB_ID
                )
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

    fun onRemoveImage() {
        _uiState.update { it.copy(imageFile = null, imageBitmap = null) }
    }

    fun onClickPost() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                createThreadUseCase(
                    clubId = args.clubId,
                    postContent = uiState.value.postContent,
                    privacy = uiState.value.privacy,
                    imageFile = uiState.value.imageFile
                )
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }

            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }
}