package com.devfalah.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var searchJob: Job? = null

    fun onSearchTextChange(keyword: String) {
        _uiState.update { it.copy(keyword = keyword) }
        onSearch()
    }

    fun onSearch() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            try {
                _uiState.update { it.copy(isLoading = true, error = "") }
                val searchResult = getSearchUseCase(uiState.value.keyword, limit = 3)
                _uiState.emit(searchResult.toUIState().copy(keyword = uiState.value.keyword))
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