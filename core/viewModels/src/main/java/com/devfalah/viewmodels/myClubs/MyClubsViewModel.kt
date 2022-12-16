package com.devfalah.viewmodels.myClubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserClubsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyClubsViewModel @Inject constructor(
    private val getUserClubs: GetUserClubsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MyClubsUiState())
    val uiState = _uiState.asStateFlow()

    private val myGuid = 4

    init {
        getMyGroups()
    }

    private fun getMyGroups() {
        viewModelScope.launch {
            val myClubs = getUserClubs(myGuid)
            _uiState.update { it.copy(myClubs = myClubs.map { club -> club.toUiState() }) }
        }
    }

    fun onClubClicked(myClub: MyClubsState) {
        Log.w("club_Clicked", myClub.title)
    }
}