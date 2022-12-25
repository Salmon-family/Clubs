package com.devfalah.viewmodels.myClubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserClubsUseCase
import com.devfalah.usecases.GetUserIdUseCase
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
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyClubsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                _uiState.update { it.copy(id = getUserIdUseCase()) }
                getMyGroups()
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    private fun getMyGroups() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val myClubs = getUserClubs(uiState.value.id)
                _uiState.update { it.copy(myClubs = myClubs.map { club -> club.toUIState() }) }
            } catch (e: Throwable) {
                _uiState.update { it.copy(error = e.message.toString()) }
                Log.e("getMyGroups", e.message.toString())
            }
        }
    }

}