package com.thechance.identity.repositories

import com.thechance.identity.entities.Club


interface LocalIdentityDataSource {
    fun getStartInstall(): Boolean?
    suspend fun setStartInstall(value: Boolean)

    fun getUserId(): Int?
    suspend fun saveUserId(id: Int)

    fun getClubs(): List<Club>

    suspend fun saveToken(token: String)

    fun getToken(): String
}