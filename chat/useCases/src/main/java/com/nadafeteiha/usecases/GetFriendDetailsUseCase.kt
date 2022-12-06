package com.nadafeteiha.usecases

import javax.inject.Inject

class GetFriendDetailsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(friendID: Int) = chatRepository.getFriendDetails(friendID)
}