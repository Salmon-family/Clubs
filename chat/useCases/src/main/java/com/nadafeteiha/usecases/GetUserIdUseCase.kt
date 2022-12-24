package com.nadafeteiha.usecases

import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    operator fun invoke():Int{
        return 3
    }
}