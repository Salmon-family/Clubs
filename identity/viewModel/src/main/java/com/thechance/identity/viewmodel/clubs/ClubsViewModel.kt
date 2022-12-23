package com.thechance.identity.viewmodel.clubs

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.AcceptJoiningRequestUseCase
import com.thechance.identity.usecases.GetClubsUseCaase
import com.thechance.identity.usecases.GetUserIdUseCase
import com.thechance.identity.usecases.JoinClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun onJoin(){
        onLoading()
        joinClubs()
    }

    private fun joinClubs(){
        val userId = getUserIdUseCase()?.toInt() ?: 0
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    _uiState.value.selectedClubs.forEach { club ->
                        joinClubUseCase(club.id, userId)
                        acceptJoiningRequestUseCase(club.id, userId, OWNER_ID)
                    }
                }
                onSuccess()
            }catch (t: Throwable) {
                onError(errorMessage = t)
            }
        }
    }

    private fun onLoading() {
        _uiState.update { it.copy(isLoading = true, isSuccess = false, errorMessage = "") }
    }

    private fun onSuccess() {
        _uiState.update { it.copy(isSuccess = true, errorMessage = "", isLoading = false) }
    }

    private fun onError(errorMessage: Throwable) {
        _uiState.update {
            it.copy(
                errorMessage = errorMessage.message.toString(),
                isSuccess = false,
                isLoading = false
            )
        }
    }

    companion object{
        private const val OWNER_ID = 16
    }
}