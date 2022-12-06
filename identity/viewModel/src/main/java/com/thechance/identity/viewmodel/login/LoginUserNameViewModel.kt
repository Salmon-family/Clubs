package com.thechance.identity.viewmodel.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginUserNameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUserNameUIState())
    val uiState = _uiState.asStateFlow()

    fun onChangeUserName(userName: String){
        _uiState.update { it.copy(userName = userName) }
    }
}