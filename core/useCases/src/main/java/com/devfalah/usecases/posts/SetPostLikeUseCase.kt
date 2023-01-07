package com.devfalah.usecases.posts

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetPostLikeUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(postID: Int, isLiked: Boolean): Int {
        val userId = clubRepository.getUserId()
        return if (isLiked) {
            clubRepository.removeLikeOnPost(userID = userId, postId = postID)
        } else {
            clubRepository.setLikeOnPost(userID = userId, postId = postID)
        }
    }

}