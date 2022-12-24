package com.devfalah.usecases.posts

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class MangeCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend fun addComment(userId: Int, postId: Int, comment: String): Comment {
        return clubRepository.addComment(userId = userId, postId = postId, comment = comment)
    }

    suspend fun deleteComment(userId: Int, commentId: Int): Boolean {
        return clubRepository.deleteComment(userId = userId, commentId = commentId)
    }

    suspend fun editComment(commentId: Int, comment: String): Boolean {
        return clubRepository.editComment(commentId = commentId, comment = comment)
    }

}