package com.thechance.identity.viewmodel.onBoarder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.identity.usecases.GetStartOnBoardingValueUseCase
import com.thechance.identity.usecases.SaveStartOnBoardingValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartOnBoarderViewModel @Inject constructor(
    private val getStartOnBoardingValueUseCase: GetStartOnBoardingValueUseCase,
    private val saveStartOnBoardingValueUseCase: SaveStartOnBoardingValueUseCase
) : ViewModel() {

    init {
        saveStartValue()
    }

    private fun saveStartValue() {
        viewModelScope.launch {
            saveStartOnBoardingValueUseCase(true)
        }
    }

    fun getStartValue(): Boolean {
        var value = false
        viewModelScope.launch {
            value = getStartOnBoardingValueUseCase.invoke()
        }

        return value
    }
}