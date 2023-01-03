package com.nadafeteiha.usecases

import javax.inject.Inject

class SearchForFriendsUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    operator fun invoke(query: String) = repository.getFriends(query)
}