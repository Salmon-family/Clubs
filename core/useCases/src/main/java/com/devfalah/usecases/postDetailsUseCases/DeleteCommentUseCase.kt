package com.devfalah.usecases.postDetailsUseCases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class DeleteCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(userId: Int, commentId: Int): Boolean {
        return clubRepository.deleteComment(userId, commentId)
    }
}