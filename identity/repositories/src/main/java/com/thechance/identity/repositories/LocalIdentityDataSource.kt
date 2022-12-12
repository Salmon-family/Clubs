package com.thechance.identity.repositories


interface LocalIdentityDataSource {
    fun getStartInstall(): Boolean?
    suspend fun setStartInstall(value: Boolean)

    fun getUserId(): String?
    suspend fun saveUserId(id: String)
}