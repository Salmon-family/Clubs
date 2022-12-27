package com.devfalah.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetSearchUseCase
import com.devfalah.viewmodels.friends.toFriendsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState = _uiState.asStateFlow()

    fun onSearchTextChange(keyword: String) {
        _uiState.update { it.copy(keyword = keyword) }
        onSearch()
    }

    fun onSearch() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                if (uiState.value.keyword.isNotBlank()) {
                    delay(2000)
                    val searchResult = getSearchUseCase(uiState.value.keyword)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            clubs = searchResult.clubs.toUIState().take(3),
                            showSeeAllClubs = searchResult.clubs.size > 3,
                            users = searchResult.users.toFriendsUIState().take(3),
                            showSeeAllUsers = searchResult.clubs.size > 3,
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(isLoading = false, clubs = emptyList(), users = emptyList())
                    }
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = t.message.toString(),
                        users = emptyList(),
                        clubs = emptyList()
                    )
                }
            }
        }
    }


}