package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetHomePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(userId: Int): List<Post> {
        return clubRepository.getProfilePosts(userId, userId)
    }

    private suspend fun getHomePosts(userId: Int): List<Post> {
        return clubRepository.getProfilePosts(userId, userId)
    }

    private fun getUserGroupsPosts(){

    }

}