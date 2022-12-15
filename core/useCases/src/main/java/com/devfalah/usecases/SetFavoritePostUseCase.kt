package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetFavoritePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(post: Post) {
        if (isPostSaved(post.id)) {
            deletePostLocally(post.id)
        } else {
            savePostLocally(post)
        }
    }

    private suspend fun isPostSaved(postId: Int): Boolean {
        return clubRepository.isPostSavedLocally(postId)
    }

    private suspend fun savePostLocally(post: Post) {
        clubRepository.savedPosted(post)
    }

    suspend fun deletePostLocally(postId: Int) {
        clubRepository.deletePost(postId)
    }
}
