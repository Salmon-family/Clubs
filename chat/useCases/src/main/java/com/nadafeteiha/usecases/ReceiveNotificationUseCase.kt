package com.nadafeteiha.usecases

import kotlinx.coroutines.flow.filterNot
import javax.inject.Inject

class ReceiveNotificationUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(){
         chatRepository.onReceiveId().filterNot { it==0 }.collect{
            println("DEVFALAHMESSAGE $it")
        }
    }
}