package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import com.devfalah.usecases.util.Constants.HOME_GROUP_ID
import javax.inject.Inject

class GetHomeThreadsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private var page = 1
    private lateinit var savedPosts: List<Int>
    private var userId: Int

    init {
        userId = clubRepository.getUserId()
    }

    suspend operator fun invoke() {
        getSavedPostsIds()
        userId = clubRepository.getUserId()
    }

    suspend fun loadData(): List<Post> {
        if (userId == 0) {
            userId = clubRepository.getUserId()
        }
        val homePosts = clubRepository.getUserHomePosts(userId, page).map { post ->
            if (post.id in savedPosts) {
                post.copy(isSaved = true, isMyPost = userId == post.publisherId)
            } else {
                post.copy(isMyPost = userId == post.publisherId)
            }
        }

        if (homePosts.isNotEmpty()) {
            page += 1
        }
        return homePosts
    }

    private suspend fun getSavedPostsIds() {
        return clubRepository.getSavedPostedIds(HOME_GROUP_ID).collect {
            savedPosts = it
        }
    }

}