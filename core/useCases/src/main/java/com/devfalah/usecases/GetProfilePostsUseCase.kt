package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private lateinit var savedPosts: Flow<List<Int>>

    suspend operator fun invoke(userId: Int, profileUserID: Int): List<Post> {
        savedPosts = getSavedPostsIds()
        return clubRepository.getProfilePostsPager(userId, profileUserID, page = page)
    }

    suspend fun loadMore(userId: Int, profileUserID: Int): List<Post> {
        val posts = clubRepository.getProfilePostsPager(userId, profileUserID, page = page)
        if (posts.isNotEmpty()){ page += 1 }
        return posts
    }

    private suspend fun getSavedPostsIds(): Flow<List<Int>> {
        return clubRepository.getSavedPostedIds()
    }

}