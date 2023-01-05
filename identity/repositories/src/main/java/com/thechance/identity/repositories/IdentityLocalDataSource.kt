package com.thechance.identity.repositories

interface IdentityLocalDataSource {
    suspend fun clearAllTables()
}