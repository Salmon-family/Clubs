package com.nadafeteiha.usecases

import kotlinx.coroutines.flow.filterNot
import javax.inject.Inject

class ReceiveNotificationUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getChatWithFriend: GetChatWithFriendUseCase,
) {
    suspend operator fun invoke(userID:Int){
         chatRepository.onReceiveMessage().filterNot { it==0 }.collect{
             getChatWithFriend.refreshMessages(userID = userID, friendID = it,1)
         }
    }
}