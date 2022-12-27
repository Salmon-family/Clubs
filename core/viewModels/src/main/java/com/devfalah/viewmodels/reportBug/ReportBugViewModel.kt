package com.devfalah.viewmodels.reportBug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.AddBugReportUseCase
import com.devfalah.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportBugViewModel @Inject constructor(
    private val getUserId: GetUserIdUseCase,
    private val addBugReport: AddBugReportUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportBugUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val id = getUserId()
                _uiState.update { it.copy(userId = id, isLoading = false) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString(), isLoading = false) }
            }
        }
    }

    fun onMessageChange(text: String) {
        _uiState.update { it.copy(bugMessage = text) }
    }

    fun onClickSend() {
        _uiState.update { it.copy(isLoading = true) }
        addBugReport()
    }

    private fun addBugReport() {
        addBugReport(
            userId = uiState.value.userId,
            message = uiState.value.bugMessage,
            onSuccess = {
                _uiState.update { it.copy(isSuccessful = true, isLoading = false) }
            },
            onFail = { e ->
                _uiState.update { it.copy(error = e.message.toString(), isLoading = false) }
            }
        )
    }

}