package com.devfalah.usecases.posts

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class CreateThreadUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(
        clubId: Int, postContent: String, privacy: Int, imageFile: File?
    ): Post {
        val userId = clubRepository.getUserId()
        val publisherWall = getPublishPlace(clubId = clubId, userId = userId)
        val post = if (imageFile != null) {
            clubRepository.publishPostWithImage(
                userId = userId,
                publishOnId = publisherWall,
                postContent = postContent,
                privacy = privacy,
                imageFile = imageFile
            )
        } else {
            clubRepository.publishPostUserWall(userId, publisherWall, postContent, privacy)
        }
        return post
    }

    private fun getPublishPlace(clubId: Int, userId: Int): Int {
        return if (clubId == 0 || clubId == -1) {
            userId
        } else {
            clubId
        }
    }
}
