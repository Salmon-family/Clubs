package com.devfalah.usecases.posts

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class ManageCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private val userId = clubRepository.getUserId()

    suspend fun addComment(postId: Int, comment: String): Comment {
        val result = clubRepository.addComment(userId = userId, postId = postId, comment = comment)
        return result.copy(isMyComment = true)
    }

    suspend fun deleteComment(commentId: Int): Boolean {
        return clubRepository.deleteComment(userId = userId, commentId = commentId)
    }

    suspend fun editComment(comment: String): Boolean {
        return clubRepository.editComment(commentId = userId, comment = comment)
    }

}