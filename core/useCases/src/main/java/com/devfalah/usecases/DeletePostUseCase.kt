package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(userId: Int, postId: Int): Boolean {
        return clubRepository.deletePost(userId, postId)
    }
}