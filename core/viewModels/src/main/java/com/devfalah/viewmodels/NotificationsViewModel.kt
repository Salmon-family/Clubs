package com.devfalah.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetNotificationsUseCase
import com.devfalah.usecases.GetUserFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getNotifications: GetNotificationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    init {
        Log.i("kkk"," notifications.toString()")

        viewModelScope.launch {
            val notifications = getNotifications(6)
            _uiState.update { notifications.toString() }
            Log.i("kkk", notifications.toString())
        }
    }

}