package com.nadafeteiha.usecases

import com.thechance.entities.User
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(userID: Int): User{
        return chatRepository.getUserDetail(userID)
    }
}