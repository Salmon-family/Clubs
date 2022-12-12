package com.nadafeteiha.usecases

import kotlinx.coroutines.flow.filterNot
import javax.inject.Inject

class ReceiveNotificationUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(){
         chatRepository.onReceiveMessage().filterNot { it.friendId==0 }.collect{
             chatRepository.insertMessage(it)
             chatRepository.updateRecentMessage(it.friendId,it.message)
        }
    }
}