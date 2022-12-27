package com.devfalah.viewmodels.reportBug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.AddBugReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportBugViewModel @Inject constructor(
    private val addBugReport: AddBugReportUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportBugUiState())
    val uiState = _uiState.asStateFlow()

    fun onMessageChange(text: String) {
        _uiState.update { it.copy(bugMessage = text) }
    }

    fun onClickSend() {
        viewModelScope.launch {
            addBugReport(
                message = uiState.value.bugMessage,
                onSuccess = {
                    _uiState.update { it.copy(isSuccessful = true) }
                },
                onFail = { e ->
                    _uiState.update { it.copy(error = e.message.toString()) }
                }
            )
        }
    }

}