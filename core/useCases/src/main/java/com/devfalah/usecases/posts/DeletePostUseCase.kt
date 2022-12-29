package com.devfalah.usecases.posts

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(postId: Int): Boolean {
        return clubRepository.deletePost(clubRepository.getUserId(), postId)
    }
}