package com.devfalah.repository

import com.devfalah.repository.models.MessageEntityLocalDTO
import com.devfalah.repository.models.ChatLocalDto
import com.devfalah.repository.models.FriendDTOLocal
import kotlinx.coroutines.flow.Flow

interface ChatLocalDataSource {

    suspend fun insertChats(chats: List<ChatLocalDto>)

    fun getChats(): Flow<List<ChatLocalDto>>

    fun getChats(query: String): Flow<List<ChatLocalDto>>

    suspend fun insertMessages(messages: List<MessageEntityLocalDTO>)

    suspend fun insertMessage(message: MessageEntityLocalDTO)

    fun getMessages(friendId: Int): Flow<List<MessageEntityLocalDTO>>

    suspend fun updateRecentMessage(id: Int, recentMessage: String)

    fun getFriends(): Flow<List<FriendDTOLocal>>

    fun getFriends(query: String): Flow<List<FriendDTOLocal>>

    suspend fun insertFriends(friends: List<FriendDTOLocal>)
}