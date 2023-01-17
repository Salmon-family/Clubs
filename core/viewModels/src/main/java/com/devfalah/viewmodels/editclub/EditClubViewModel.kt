package com.devfalah.viewmodels.editclub

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.EditClubUseCase
import com.devfalah.viewmodels.clubCreation.ClubPrivacy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditClubViewModel @Inject constructor(
    private val editClub: EditClubUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditClubUiState())
    val uiState = _uiState.asStateFlow()

    private val args = EditClubArgs(savedStateHandle)

    init {
        _uiState.update {
            it.copy(
                clubId = args.clubId,
                clubName = args.clubName,
                clubDescription = args.clubDescription,
                privacy = if (args.privacyType == "true") {
                    ClubPrivacy.PUBLIC
                } else {
                    ClubPrivacy.PRIVATE
                }
            )
        }
    }

    fun onChangedClubName(clubName: String) {
        _uiState.update { it.copy(clubName = clubName) }
    }

    fun onDescriptionChanged(clubDescription: String) {
        _uiState.update { it.copy(clubDescription = clubDescription) }
    }

    fun onPrivacyChanged(type: Int) {
        val privacy = when (type) {
            0 -> ClubPrivacy.PUBLIC
            1 -> ClubPrivacy.PRIVATE
            else -> ClubPrivacy.PUBLIC
        }
        _uiState.update { it.copy(privacy = privacy) }
    }

    fun onClickEditClub() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                editClub(
                    args.clubId,
                    _uiState.value.clubName.trim(),
                    _uiState.value.clubDescription.trim(),
                    _uiState.value.privacy.value
                )
                _uiState.update { it.copy(isLoading = false, isSuccessful = true) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isLoading = false, isSuccessful = false) }
            }
        }
    }
}