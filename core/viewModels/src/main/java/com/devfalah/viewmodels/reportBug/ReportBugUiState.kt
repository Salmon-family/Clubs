package com.devfalah.viewmodels.reportBug

data class ReportBugUiState(
    val userId: Int = 0,
    val bugMessage: String = "",
    val isSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)