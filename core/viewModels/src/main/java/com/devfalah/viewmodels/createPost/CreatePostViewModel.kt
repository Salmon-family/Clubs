package com.devfalah.viewmodels.createPost

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

const val publisherId = "publisherId"

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val ownerID = checkNotNull(savedStateHandle[publisherId]).toString().toInt()

    private val _uiState = MutableStateFlow(PostCreationUIState())
    val uiState = _uiState.asStateFlow()


}