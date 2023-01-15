package com.devfalah.usecases.posts

import com.devfalah.entities.Comment
import com.devfalah.entities.NotificationRequest
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class ManageCommentUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private val userId = clubRepository.getUserId()

    suspend fun addComment(
        postId: Int,
        comment: String,
        publisherId: Int,
        clubName: String = ""
    ): Comment {
        val result = clubRepository.addComment(userId = userId, postId = postId, comment = comment)
        sendCommentNotification(result.user.name, clubName, publisherId)
        return result.copy(isMyComment = true)
    }

    suspend fun deleteComment(commentId: Int): Boolean {
        return clubRepository.deleteComment(userId = userId, commentId = commentId)
    }

    suspend fun editComment(comment: String): Boolean {
        return clubRepository.editComment(commentId = userId, comment = comment)
    }

    private suspend fun sendCommentNotification(
        userName: String,
        clubName: String,
        publisherId: Int
    ) {
        val publisherDetails = clubRepository.getUserAccountDetails(publisherId)
        if (clubName.isEmpty()) {
            sendAddPostCommentNotification(userName, publisherDetails.token)
        } else {
            sendAddClubPostCommentNotification(userName, clubName, publisherDetails.token)
        }
    }

    private suspend fun sendAddPostCommentNotification(userName: String, publisherToken: String) {
        clubRepository.pushNotification(
            NotificationRequest(
                body = userName,
                to = publisherToken,
                clickAction = "addPostComment"
            )
        )
    }

    private suspend fun sendAddClubPostCommentNotification(
        userName: String,
        clubName: String,
        publisherToken: String
    ) {
        clubRepository.pushNotification(
            NotificationRequest(
                title = clubName,
                body = userName,
                to = publisherToken,
                clickAction = "addClubPostComment"
            )
        )
    }




}