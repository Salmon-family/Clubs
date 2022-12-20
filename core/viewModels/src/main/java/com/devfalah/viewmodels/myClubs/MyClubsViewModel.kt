package com.devfalah.viewmodels.myClubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserClubsUseCase
import com.devfalah.viewmodels.search.ClubUIState
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
): ViewModel() {

    private val _uiState = MutableStateFlow(MyClubsUiState())
    val uiState = _uiState.asStateFlow()

    private val myGuid = 2

    init {
        getMyGroups()
    }

    private fun getMyGroups() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val myClubs = getUserClubs(myGuid)
                _uiState.update { it.copy(myClubs = myClubs.map { club -> club.toUIState() }) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(error = e.message.toString()) }
                Log.e("getMyGroups", e.message.toString())
            }
        }
    }

    fun onClubClicked(clubId: Int) {
        Log.w("club_Clicked", clubId.toString())
    }
}