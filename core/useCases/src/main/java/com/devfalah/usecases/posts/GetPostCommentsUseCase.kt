package com.devfalah.usecases.posts

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1

    suspend operator fun invoke(postId: Int, userID: Int): List<Comment> {
        return clubRepository.getPostComments(postId = postId, userId = userID, page)
    }


}