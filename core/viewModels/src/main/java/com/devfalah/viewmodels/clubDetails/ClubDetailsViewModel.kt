package com.devfalah.viewmodels.clubDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetClubDetailsUseCase
import com.devfalah.usecases.GetClubMembersUseCase
import com.devfalah.usecases.GetGroupWallUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubDetailsViewModel @Inject constructor(
    private val getClubDetailsUseCase: GetClubDetailsUseCase,
    private val getClubMembersUseCase: GetClubMembersUseCase,
    private val getGroupWallUseCase: GetGroupWallUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = ClubDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ClubDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getClubDetails()
    }

    private fun getClubDetails() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val clubDetails =
                    getClubDetailsUseCase(userID = args.userID, groupID = args.groupId)

                _uiState.update {
                    it.copy(
                        name = clubDetails.name,
                        description = clubDetails.description,
                        privacy = getPrivacy(clubDetails.privacy),
                        membersCount = getClubMembersUseCase(args.groupId),
                        postCount = getGroupWallUseCase(args.userID, args.groupId).count,
                        isLoading = false,
                        isSuccessful = true
                    )
                }
            } catch (throwable: Throwable) {
                _uiState.update { it.copy(isLoading = false, isSuccessful = false) }
            }
        }
    }


    private fun getPrivacy(value: String): String {
        return when (value) {
            "1" -> PRIVATE_PRIVACY
            else -> PUBLIC_PRIVACY
        }
    }


    companion object {
        private const val PRIVATE_PRIVACY = "Private"
        private const val PUBLIC_PRIVACY = "Public"
    }
}