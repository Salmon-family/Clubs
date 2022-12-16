package com.devfalah.usecases.postDetailsUseCases

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class SetCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(userId: Int, postId: Int, comment: String/*, imageFile: File*/): Comment {
        return clubRepository.addComment(userId, postId, comment/*, imageFile*/)
    }
}
