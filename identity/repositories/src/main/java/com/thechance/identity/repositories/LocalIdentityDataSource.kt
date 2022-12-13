package com.thechance.identity.repositories

import com.thechance.identity.entities.Club


interface LocalIdentityDataSource {
    fun getStartInstall(): Boolean?
    suspend fun setStartInstall(value: Boolean)

    fun getUserId(): String?
    suspend fun saveUserId(id: String)

    fun getClubs(): List<Club>
}