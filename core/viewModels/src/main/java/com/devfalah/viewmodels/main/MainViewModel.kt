package com.devfalah.viewmodels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.user.GetUserIdUseCase
import com.devfalah.usecases.user.SaveUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase
): ViewModel() {

    fun getUserId() = getUserIdUseCase()

    fun saveUserId(userId: Int) {
        viewModelScope.launch {
            saveUserIdUseCase(userId)
        }
    }
}