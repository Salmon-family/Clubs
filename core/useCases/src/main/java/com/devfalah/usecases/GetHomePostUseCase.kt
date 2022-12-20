package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetHomePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private var page = 1
    private lateinit var savedPosts: List<Int>

    suspend operator fun invoke() {
        getSavedPostsIds()
    }

    suspend fun loadData(userId: Int): List<Post> {
        val homePosts = clubRepository.getUserHomePosts(userId, page).map { post ->
            if (post.id in savedPosts) {
                post.copy(isSaved = true)
            } else {
                post
            }
        }

        if (homePosts.isNotEmpty()) {
            page += 1
        }
        return homePosts
    }

    private suspend fun getSavedPostsIds() {
        return clubRepository.getSavedPostedIds().collect {
            savedPosts = it
        }
    }

}