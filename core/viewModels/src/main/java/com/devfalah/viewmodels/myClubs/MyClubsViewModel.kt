package com.devfalah.viewmodels.myClubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserClubsUseCase
import com.devfalah.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyClubsViewModel @Inject constructor(
    private val getUserClubs: GetUserClubsUseCase,
    private val search: SearchUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MyClubsUiState())
    val uiState = _uiState.asStateFlow()

    private val _clubsUiState = MutableStateFlow(ClubsUiState())
    val clubsUiState = _clubsUiState.asStateFlow()

    private val myGuid = 4

    init {
        getMyGroups()
        searchForClubs()

    }

    private fun getMyGroups() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val myClubs = getUserClubs(myGuid)
            _uiState.update { it.copy(myClubs = myClubs.map { club -> club.toUiState() }) }
        }
    }

    private fun searchForClubs() {
        viewModelScope.launch {
            _clubsUiState.update { it.copy(isLoading = true) }
            val clubs = search(myGuid, "group")
            _clubsUiState.update { it.copy(clubs = clubs.groups.map { club -> club.toUiState() },
            isLoading = false) }
            Log.w("searchResult", clubsUiState.toString())
        }
    }

    fun onClubClicked(myClub: ClubsState) {
        Log.w("club_Clicked", myClub.title)
    }
}