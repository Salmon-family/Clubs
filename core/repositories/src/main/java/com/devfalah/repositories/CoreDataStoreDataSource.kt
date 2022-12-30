package com.devfalah.repositories

interface CoreDataStoreDataSource {
    fun getUserId(): Int?

    suspend fun saveUserId(userId: Int)

    suspend fun deleteUserId()
}