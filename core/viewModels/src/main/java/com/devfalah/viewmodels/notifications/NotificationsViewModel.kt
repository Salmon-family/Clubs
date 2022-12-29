package com.devfalah.viewmodels.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.notification.GetNotificationsUseCase
import com.devfalah.usecases.notification.MarkNotificationAsViewedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getNotifications: GetNotificationsUseCase,
    private val markNotificationAsViewed: MarkNotificationAsViewedUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserNotifications()
    }

    fun getUserNotifications() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val notifications = getNotifications()
                _uiState.update {
                    it.copy(
                        notifications = notifications.toUIState(),
                        isLoading = false,
                        error = ""
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    fun markNotificationAsViewed(notification: NotificationState) {
        viewModelScope.launch {
            try {
                markNotificationAsViewed(notification.id)
            } catch (t: Throwable) {
                Log.e("markNotificationError", t.message.toString())
            }
        }
    }

}