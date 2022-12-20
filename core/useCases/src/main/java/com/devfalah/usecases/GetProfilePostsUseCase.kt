package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private lateinit var savedPosts: List<Int>

    suspend operator fun invoke(userId: Int, profileUserID: Int): List<Post> {
        getSavedPostsIds()
        return clubRepository.getProfilePostsPager(userId, profileUserID, page = page)
    }

    suspend fun loadMore(userId: Int, profileUserID: Int): List<Post> {
        val posts = clubRepository.getProfilePostsPager(userId, profileUserID, page = page)
        if (posts.isNotEmpty()) {
            page += 1
        }
        return posts.map { post ->
            if (post.id in savedPosts) {
                post.copy(isSaved = true)
            } else {
                post
            }
        }
    }

    private suspend fun getSavedPostsIds() {
        return clubRepository.getSavedPostedIds().collect {
            savedPosts = it
        }
    }

}