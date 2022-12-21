package com.devfalah.viewmodels.clubDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetClubDetailsUseCase
import com.devfalah.usecases.GetClubMembersUseCase
import com.devfalah.usecases.GetGroupWallUseCase
import com.devfalah.viewmodels.clubDetails.mapper.toUIState
import com.devfalah.viewmodels.clubDetails.mapper.toUserUIState
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
        getData()
    }

    fun getData() {
        getClubDetails()
        getMemberCount()
        getPostCount()
        getMembers()
        getClubPost()

    }

    private fun getClubDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = "") }
            try {
                val clubDetails =
                    getClubDetailsUseCase(userID = args.userID, groupID = args.groupId)

                _uiState.update {
                    it.copy(
                        name = clubDetails.name,
                        description = clubDetails.description,
                        privacy = getPrivacy(clubDetails.privacy),
                        isLoading = false,
                        isSuccessful = true
                    )
                }
            } catch (throwable: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = false,
                        errorMessage = throwable.message.toString()
                    )
                }
            }
        }
    }

    private fun getMemberCount() {
        viewModelScope.launch {
            try {
                val memberCount = getClubMembersUseCase(args.groupId).size
                _uiState.update { it.copy(membersCount = memberCount) }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false, isSuccessful = false, errorMessage = t.message.toString()
                    )
                }
            }
        }

    }

    private fun getPostCount() {
        viewModelScope.launch {
            try {
                val postCount = getGroupWallUseCase(args.userID, args.groupId).count
                _uiState.update { it.copy(postCount = postCount) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    /*
     viewModelScope.launch {
            try {

            }catch (t: Throwable){

            }
        }
     */

    private fun getPrivacy(value: String): String {
        return when (value) {
            "1" -> PRIVATE_PRIVACY
            else -> PUBLIC_PRIVACY
        }
    }

    private fun getMembers() {
        viewModelScope.launch {
            try {
                val members = getClubMembersUseCase(args.groupId).toUserUIState()
                _uiState.update { it.copy(members = members) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }

    private fun getClubPost() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(posts = getGroupWallUseCase(args.userID, args.groupId).post.toUIState())
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(errorMessage = t.message.toString()) }
            }
        }
    }


    companion object {
        private const val PRIVATE_PRIVACY = "Private"
        private const val PUBLIC_PRIVACY = "Public"
    }
}