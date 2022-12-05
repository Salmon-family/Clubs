package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetLikeUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(userId: Int, postID: Int, isLiked: Boolean): Int {
        return if (isLiked) {
            clubRepository.removeLikeOnPost(userID = userId, postId = postID)
        } else {
            clubRepository.setLikeOnPost(userID = userId, postId = postID)
        }
    }

}