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
    private val getUserId: GetUserIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyClubsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        val myLocalClubs = Clubs().getClubs()
        viewModelScope.launch {
            _uiState.update { it.copy(id = getUserId()) }
            _uiState.update { it.copy(specialClubs = myLocalClubs.toUIState()) }
            getMyGroups()
        }
    }

    private fun getMyGroups() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val myClubs = getUserClubs(_uiState.value.id)
                val specialClubs = _uiState.value.specialClubs.map { it.id }
                val mySpecialClubs = myClubs.filter { specialClubs.contains(it.id) }
                _uiState.update { it ->
                    it.copy(
                        myClubs = myClubs.filter { !specialClubs.contains(it.id) }.toUIState(),
                        mySpecialClubs = mySpecialClubs.map {
                            SpecialClubsUIState(
                                id = it.id,
                                name = it.name,
                                // I think this is the problem because image is local
                                image = _uiState.value.specialClubs.first { it.id == it.id }.image
                            )
                        }
                    )
                }
                Log.i("MyClubsViewModel", "getMyGroups: $myClubs")
            } catch (e: Throwable) {
                _uiState.update { it.copy(error = e.message.toString()) }
                Log.e("getMyGroups", e.message.toString())
            }
        }
    }
}
