package com.devfalah.usecases.posts

import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SetPostLikeUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {

    suspend operator fun invoke(
        postID: Int,
        isLiked: Boolean,
        publisherId: Int,
        clubName: String = ""
    ) {
        val userId = clubRepository.getUserId()
        if (isLiked) {
            clubRepository.removeLikeOnPost(userID = userId, postId = postID)
        } else {
            clubRepository.setLikeOnPost(userID = userId, postId = postID)
            sendNotification(userId, publisherId, clubName)
        }
    }

    private suspend fun sendNotification(userId: Int, publisherId: Int, clubName: String) {
        try {
            val publisherDetails = clubRepository.getUserAccountDetails(publisherId)
            val userDetails = clubRepository.getUserAccountDetails(userId)

            if (clubName.isEmpty()) {
                sendLikePostNotification(userDetails.name, publisherDetails.token)
            } else {
                sendLikeGroupPostNotification(userDetails.name, publisherDetails.token, clubName)
            }


        } catch (throwable: Throwable) {
            println(throwable.message)
        }
    }

    private suspend fun sendLikePostNotification(userName: String, publisherToken: String) {
        clubRepository.pushNotification(
            NotificationRequest(
                body = userName,
                to = publisherToken,
                clickAction = "likePost"
            )
        )
    }

    private suspend fun sendLikeGroupPostNotification(
        userName: String,
        publisherToken: String,
        clubName: String
    ) {
        clubRepository.pushNotification(
            NotificationRequest(
                title = clubName,
                body = userName,
                to = publisherToken,
                clickAction = "likeGroupPost"
            )
        )
    }

}