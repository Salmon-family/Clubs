package com.devfalah.usecases.postDetailsUseCases

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(userId: Int, postId: Int, comment: String): Comment {
        return clubRepository.addComment(userId, postId, comment)
    }
}
