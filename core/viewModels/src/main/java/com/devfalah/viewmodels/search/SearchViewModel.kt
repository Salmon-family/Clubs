package com.devfalah.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
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

    @OptIn(FlowPreview::class)
    fun onSearch() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            _uiState.debounce(1000).collectLatest {
                try {
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


}