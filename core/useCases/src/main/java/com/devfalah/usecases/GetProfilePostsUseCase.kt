package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int, profileUserID:Int): List<Post> {
        return clubRepository.getProfilePosts(userId, profileUserID)
    }
}