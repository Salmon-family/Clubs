package com.devfalah.usecases

import com.devfalah.usecases.utilities.Constants.POST
import javax.inject.Inject

class SetLikeUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(userId: Int, postID: Int, isLiked: Boolean): Int {
        return if (isLiked) {
            clubRepository.removeLike(userID = userId, postId = postID, type = POST)
        } else {
            clubRepository.setLike(userID = userId, postId = postID, type = POST)
        }
    }

}