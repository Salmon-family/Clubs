package com.nadafeteiha.usecases

import kotlinx.coroutines.flow.filterNot
import javax.inject.Inject

class ReceiveNotificationUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getChatWithFriend: GetChatWithFriendUseCase,
    private val getUserId: GetUserIdUseCase,
) {
    suspend operator fun invoke(){
         chatRepository.onReceiveMessage().filterNot { it==0 }.collect{
             getChatWithFriend.refreshMessages(userID = getUserId(), friendID = it,1)
         }
    }
}