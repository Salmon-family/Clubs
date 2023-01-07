package com.devfalah.viewmodels.clubCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.CreateClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubCreationViewModel @Inject constructor(
    private val createClub: CreateClubUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ClubCreationUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameTextChange(text: String) {
        _uiState.update { it.copy(name = text) }

    }

    fun onDescriptionTextChange(text: String) {
        _uiState.update { it.copy(description = text) }

    }

    fun onPrivacyChange(selectedItemIndex: Int) {
        val privacy = when (selectedItemIndex) {
            0 -> ClubPrivacy.PUBLIC
            1 -> ClubPrivacy.PRIVATE
            else -> ClubPrivacy.PUBLIC
        }
        _uiState.update { it.copy(privacy = privacy) }
    }


    fun onClickCreateClub() {
        createClub()
    }

    private fun createClub() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val club = createClub(
                    groupName = _uiState.value.name,
                    description = _uiState.value.description,
                    groupPrivacy = _uiState.value.privacy.value
                )
                _uiState.update { it.copy(isLoading = false, isSuccessful = true) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isLoading = false, isSuccessful = false) }
            }
        }
    }
}