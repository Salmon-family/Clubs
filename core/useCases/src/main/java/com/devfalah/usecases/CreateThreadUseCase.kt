package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class CreateThreadUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(userId: Int, postContent: String, privacy: Int, imageFile: File?)
            : Post {
        val post = if (imageFile != null) {
            clubRepository.publishPostWithImage(userId, userId, postContent, privacy, imageFile)
        } else {
            clubRepository.publishPostUserWall(userId, userId, postContent, privacy)
        }
        return post
    }
}