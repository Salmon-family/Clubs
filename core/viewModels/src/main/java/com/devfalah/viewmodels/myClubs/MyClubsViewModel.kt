package com.devfalah.viewmodels.myClubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserClubsUseCase
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

    private fun getData() {
        viewModelScope.launch {
            val myLocalClubs = specialClubs.map {
                SpecialClubsUIState(
                    id = it.key,
                    name = it.value.name,
                    image = it.value.iconId
                )
            }
            _uiState.update { it.copy(specialClubs = myLocalClubs) }
            getMyClubs()
        }
    }

    private fun getMyClubs() {
        viewModelScope.launch {
            try {
                val myClubs = getUserClubs(specialClubs.map { it.key })
                _uiState.update {
                    it.copy(
                        myClubs = myClubs.myClubs.toUIState(),
                        mySpecialClubs = myClubs.mySpecialClubs.toSpecialClubUiState()
                    )
                }
            } catch (t: Throwable) {
                Log.e("clubsError", t.message.toString())
                _uiState.update { it.copy(error = t.message.toString()) }
            }

        }
    }

}
