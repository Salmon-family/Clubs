package com.devfalah.usecases.posts

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(postId: Int, userID: Int): Post {
        val isPostSaved = isSavedInDataBase(postId = postId)
        val post = clubRepository.getPostByID(postId = postId, userID = userID)
        return post.copy(isSaved = isPostSaved)
    }

    private suspend fun isSavedInDataBase(postId: Int): Boolean {
        return clubRepository.isPostSavedLocally(postId)
    }

}