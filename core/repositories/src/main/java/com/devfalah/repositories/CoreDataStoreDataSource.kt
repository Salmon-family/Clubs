package com.devfalah.repositories

interface CoreDataStoreDataSource {
    fun getUserId(): String?

    suspend fun saveUserId(userId: Int)

    suspend fun deleteUserId()

    fun getLanguage(): String?

    suspend fun saveLanguage(language: String)

}