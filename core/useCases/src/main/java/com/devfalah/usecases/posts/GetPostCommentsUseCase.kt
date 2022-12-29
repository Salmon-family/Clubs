package com.devfalah.usecases.posts

import com.devfalah.entities.Comment
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private val userId = clubRepository.getUserId()

    suspend operator fun invoke(postId: Int): List<Comment> {
        val comments = clubRepository.getPostComments(postId = postId, userId = userId, page)
        return if (comments.isNotEmpty()) {
            page += 1
            comments.map { it.copy(isMyComment = userId == it.user.id) }
        } else {
            emptyList()
        }
    }
}