package com.thechance.identity.viewmodel.clubs

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.GetClubsUseCaase
import com.thechance.identity.usecases.JoinClubUseCase
import com.thechance.identity.viewmodel.base.BaseViewModel
import com.thechance.identity.viewmodel.utils.ErrorMessageType
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
) : BaseViewModel() {
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
        viewModelScope.launch {
            try {
                val clubs = _uiState.value.selectedClubs.toEntity()
                joinClubUseCase(clubs)
                onSuccess()
            }catch (t: Throwable) {
                onError(errorMessage = t)
            }
        }
    }

    private fun onLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                isSuccess = false,
                errorTypeValue = ErrorMessageType.NO_ERROR.value
            )
        }
    }

    private fun onSuccess() {
        _uiState.update {
            it.copy(
                isSuccess = true,
                errorTypeValue = ErrorMessageType.NO_ERROR.value,
                isLoading = false
            )
        }
    }

    private fun onError(errorMessage: Throwable) {
        _uiState.update {
            it.copy(
                errorTypeValue = getErrorTypeValue(errorMessage),
                isSuccess = false,
                isLoading = false
            )
        }
    }
}