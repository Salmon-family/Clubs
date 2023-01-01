package com.devfalah.viewmodels.main

import androidx.lifecycle.ViewModel
import com.devfalah.usecases.language.GetLanguageUseCase
import com.devfalah.usecases.user.CheckIfLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkIfLoggedInUseCase: CheckIfLoggedInUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    init {
        onGetLanguage()
    }

    fun checkIfLoggedIn() = checkIfLoggedInUseCase()

    private fun onGetLanguage() {
        _uiState.tryEmit(getLanguageUseCase.invoke().toString())
    }

}