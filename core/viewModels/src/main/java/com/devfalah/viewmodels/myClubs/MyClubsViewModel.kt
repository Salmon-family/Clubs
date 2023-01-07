package com.devfalah.viewmodels.myClubs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.club.GetUserClubsUseCase
import com.devfalah.viewmodels.search.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyClubsViewModel @Inject constructor(
    private val getUserClubs: GetUserClubsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyClubsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        getSpecialClub()
        _uiState.update { it.copy(isLoading = true, error = "") }
        getMyClubs()

    }

    private fun getSpecialClub() {
        viewModelScope.launch {
            val myLocalClubs = specialClubs.map {
                SpecialClubsUIState(
                    id = it.key,
                    name = it.value.name,
                    image = it.value.iconId
                )
            }
            _uiState.update { it.copy(specialClubs = myLocalClubs) }
        }
    }

    private fun getMyClubs() {
        viewModelScope.launch {
            try {
                val myClubs = getUserClubs(specialClubs.map { it.key })
                _uiState.update {
                    it.copy(
                        myClubs = myClubs.myClubs.toUIState(),
                        mySpecialClubs = myClubs.mySpecialClubs.toSpecialClubUiState(),
                        isLoading = false
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }

        }
    }

    fun refreshClub() {
        getSpecialClub()
        getMyClubs()
    }

}
