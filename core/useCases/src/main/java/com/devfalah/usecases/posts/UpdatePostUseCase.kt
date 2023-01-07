package com.devfalah.usecases.posts

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(post: Post) {
        clubRepository.addHomePost(post)
    }

}