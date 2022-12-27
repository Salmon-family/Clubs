package com.devfalah.repositories

interface CoreFireStoreDataSource {
    suspend fun addBugReport(
        userId: Int,
        message: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    )
}