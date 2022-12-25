package com.devfalah.viewmodels.allSearchResult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetSearchUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.viewmodels.util.Constants.SEARCH_CLUB
import com.devfalah.viewmodels.friends.toFriendsUIState
import com.devfalah.viewmodels.search.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSearchResultViewModel @Inject constructor(
    val getUserIdUseCase: GetUserIdUseCase,
    val getSearchUseCase: GetSearchUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = AllSearchResultArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(AllSearchResultUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val userId = getUserIdUseCase()
                val result = getSearchUseCase(userId, args.keyword)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isClub = (args.type == SEARCH_CLUB),
                        title = args.title,
                        clubs = result.clubs.toUIState(),
                        users = result.users.toFriendsUIState()
                    )
                }

            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }

        }
    }

}