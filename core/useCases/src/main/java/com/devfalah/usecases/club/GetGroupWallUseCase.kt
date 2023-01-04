package com.devfalah.usecases.club

import com.devfalah.entities.GroupWallPost
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetGroupWallUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private var page = 1
    private var count = 0
    private lateinit var savedPosts: List<Int>
    private val userId: Int = clubRepository.getUserId()

    suspend operator fun invoke(groupID: Int) {
        getSavedPostsIds(groupID)
    }

    suspend fun loadMore(groupID: Int): List<GroupWallPost> {
        val clubPosts = clubRepository.getGroupWallList(userID = userId, groupID = groupID, page)
        count = clubPosts.count
        return if (clubPosts.post.isNotEmpty()) {
            page += 1
            clubPosts.post.map { clubPost ->
                if (clubPost.post.id in savedPosts) {
                    clubPost.copy(
                        post = clubPost.post.copy(
                            isSaved = true, isMyPost = userId == clubPost.post.publisherId
                        )
                    )
                } else {
                    clubPost.copy(post = clubPost.post.copy(isMyPost = userId == clubPost.post.publisherId))
                }
            }
        } else {
            emptyList()
        }
    }

    fun getPostsCount(): Int {
        return count
    }

    private suspend fun getSavedPostsIds(groupID: Int) {
        return clubRepository.getSavedPostedIds(groupID).collect {
            savedPosts = it
        }
    }
}