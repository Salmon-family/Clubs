package com.devfalah.usecases.posts

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetCommentLikeUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(commentId: Int, isLiked: Boolean): Int {
        val userId = clubRepository.getUserId()
        return if (isLiked) {
            clubRepository.removeLikeOnComment(userID = userId, commentId = commentId)
        } else {
            clubRepository.setLikeOnComment(userID = userId, commentId = commentId)
        }
    }

}