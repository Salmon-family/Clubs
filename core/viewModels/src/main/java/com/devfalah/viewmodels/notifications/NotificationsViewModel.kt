package com.devfalah.viewmodels.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetNotificationsUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.MarkNotificationAsViewedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getNotifications: GetNotificationsUseCase,
    val getUser: GetUserIdUseCase,
    private val markNotificationAsViewed: MarkNotificationAsViewedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(userId = getUser()) }
                getUserNotifications()
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    private fun getUserNotifications() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val notifications = getNotifications(4)
                _uiState.update { it.copy(notifications = notifications.toUIState()) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    fun markNotificationAsViewed(notification: NotificationState) {
        viewModelScope.launch { markNotificationAsViewed(notification.id) }

        Log.e("TEST", "Open notification ${notification.posterName}")
    }

}