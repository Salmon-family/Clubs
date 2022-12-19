package com.thechance.identity.viewmodel.clubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.AcceptJoiningRequestUseCase
import com.thechance.identity.usecases.GetClubsUseCaase
import com.thechance.identity.usecases.GetUserIdUseCase
import com.thechance.identity.usecases.JoinClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubsViewModel @Inject constructor(
    private val getClubsUseCase: GetClubsUseCaase,
    private val joinClubUseCase: JoinClubUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val acceptJoiningRequestUseCase: AcceptJoiningRequestUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ClubsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getClubs()
    }

    private fun getClubs() {
        val clubs = getClubsUseCase().toUIState()
        _uiState.update { it.copy(clubs = clubs) }
    }

    fun onChangeSelectedClub(club: ClubUIState){
        val selectedClubs = uiState.value.selectedClubs.toMutableList()

        if(selectedClubs.contains(club)){
            selectedClubs.remove(club)
        }else{
            selectedClubs.add(club)
        }

        _uiState.update { it.copy(selectedClubs = selectedClubs) }
        Log.i("selected", _uiState.value.selectedClubs.size.toString())
    }

    fun checkSelectedClubs(): Boolean{
        return _uiState.value.selectedClubs.size in 1..5
    }

    fun joinClubs(){
        val userId = getUserIdUseCase()?.toInt() ?: 0
        viewModelScope.launch {
            try {
                _uiState.value.selectedClubs.forEach { club ->
                    joinClubUseCase(club.id, userId)
                    acceptJoiningRequestUseCase(club.id, userId, OWNER_ID)
                }
            }catch (t: Throwable) {
                Log.i("error", t.message.toString())
            }
        }
    }

    companion object{
        private const val OWNER_ID = 3
    }
}