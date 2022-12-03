package com.devfalah.usecases

import com.devfalah.entities.Post
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int): List<Post> {
        return clubRepository.getProfilePosts(userId, userId)
    }
}