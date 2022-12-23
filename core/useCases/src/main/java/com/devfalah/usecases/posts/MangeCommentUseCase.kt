package com.devfalah.usecases.posts

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class MangeCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int, postId: Int, comment: String): Comment {
        return clubRepository.addComment(userId = userId, postId = postId, comment = comment)
    }


    fun deleteComment() {

    }

    fun editComment() {

    }

}