package com.devfalah.viewmodels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.language.UpdateLanguageUseCase
import com.devfalah.usecases.user.CheckIfLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkIfLoggedInUseCase: CheckIfLoggedInUseCase,
    private val getLanguageUseCase: UpdateLanguageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { getLanguageUseCase.getAppLanguage() }
        }
    }

    fun checkIfLoggedIn() = checkIfLoggedInUseCase()

    fun onGetLanguage(defaultLang: String) {
        viewModelScope.launch {
            val lang = getLanguageUseCase(defaultLang)
            _uiState.emit(lang)
        }
    }

}