package com.devfalah.repositories

interface AppConfigurator {
    fun getUserId(): String?

    suspend fun saveUserId(userId: Int)

    suspend fun deleteUserId()
}