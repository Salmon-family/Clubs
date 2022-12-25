package com.thechance.identity.repositories


interface IdentityFirebaseDataSource {
    suspend fun getToken(): String
}