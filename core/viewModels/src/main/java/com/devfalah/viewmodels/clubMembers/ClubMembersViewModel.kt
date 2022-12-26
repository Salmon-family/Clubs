package com.devfalah.viewmodels.clubMembers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetClubMembersUseCase
import com.devfalah.viewmodels.friends.FriendsUIState
import com.devfalah.viewmodels.friends.toFriendsUIState
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubMembersViewModel @Inject constructor(
    private val getClubMembersUseCase: GetClubMembersUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ClubMembersArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(FriendsUIState())
    val uiState = _uiState.asStateFlow()


    fun getClubMembers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val members = getClubMembersUseCase(args.clubId)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isPagerEnd = (members.isEmpty() || members.size < MAX_PAGE_ITEM),
                        friends = (uiState.value.friends + members.toFriendsUIState())
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }
        }
    }

}