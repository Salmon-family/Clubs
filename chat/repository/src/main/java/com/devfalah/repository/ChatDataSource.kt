package com.devfalah.repository

import com.devfalah.repository.models.ChatDTO

interface ChatDataSource {

    suspend fun getChats(userID: Int): List<ChatDTO>
}