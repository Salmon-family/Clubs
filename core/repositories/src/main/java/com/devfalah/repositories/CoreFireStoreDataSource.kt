package com.devfalah.repositories

interface CoreFireStoreDataSource {
    fun addBugReport(
        userId: Int,
        message: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    )
}