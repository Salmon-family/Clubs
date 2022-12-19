package com.devfalah.usecases.postDetailsUseCases

import com.devfalah.entities.Comment
import com.devfalah.entities.Success
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetEditCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(commentId: Int, comment: String): Success {
        return clubRepository.editComment(commentId, comment)
    }
}