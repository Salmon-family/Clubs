package com.devfalah.viewmodels.clubRequests

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.GetClubRequestsUseCase
import com.devfalah.usecases.club.ManageClubRequestsUseCase
import com.devfalah.viewmodels.friendRequest.listToUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubRequestsViewModel @Inject constructor(
    val getClubRequestsUseCase: GetClubRequestsUseCase,
    private val manageClubRequestsUseCase: ManageClubRequestsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ClubRequestsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(ClubRequestsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val requests = getClubRequestsUseCase(args.clubID)
                _uiState.update { it.copy(isLoading = false, users = requests.listToUserUiState()) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    fun acceptRequest(memberId: Int, memberToken: String) {
        viewModelScope.launch {
            try {
                if (manageClubRequestsUseCase.acceptRequest(clubId = args.clubID,
                        memberId = memberId, ownerId = args.ownerId, memberToken = memberToken)) {
                    _uiState.update {
                        it.copy(
                            users = _uiState.value.users.filterNot { it.userID == memberId },
                            isLoading = false,
                        )
                    }
                }else{
                    _uiState.update { it.copy(isLoading = false, minorError = "Failed to accept") }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, minorError = t.message.toString()) }
            }
        }
    }

    fun declineRequest(memberId: Int) {
        viewModelScope.launch {
            try {
                if (manageClubRequestsUseCase.declineRequest(clubId = args.clubID,
                        memberId = memberId, ownerId = args.ownerId)) {
                    _uiState.update {
                        it.copy(
                            users = _uiState.value.users.filterNot { it.userID == memberId },
                            isLoading = false,
                        )
                    }
                }else{
                    _uiState.update { it.copy(isLoading = false, minorError = "Failed to decline") }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, minorError = t.message.toString()) }
            }
        }
    }

}