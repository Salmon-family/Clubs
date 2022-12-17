package com.devfalah.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetSearchUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.viewmodels.friends.toFriendsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getUserIdUseCase: GetUserIdUseCase,
    val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { _uiState.update { it.copy(userId = getUserIdUseCase()) } }
    }

    fun onSearchTextChange(keyword: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, keyword = keyword) }
            try {
                if (keyword.isNotBlank()) {
                    val searchResult = getSearchUseCase(uiState.value.userId, keyword)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            clubs = searchResult.clubs.toUIState(),
                            users = searchResult.users.toFriendsUIState()
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(isLoading = false, clubs = emptyList(), users = emptyList())
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }


}