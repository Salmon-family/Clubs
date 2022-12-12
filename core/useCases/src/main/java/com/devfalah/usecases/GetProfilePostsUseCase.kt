package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private var lastPage = 1
    private val allPosts = mutableSetOf<Post>()
    private lateinit var savedPosts: Flow<List<Int>>

    suspend operator fun invoke(userId: Int, profileUserID: Int): List<Post> {
        savedPosts = getSavedPostsIds()
        allPosts.addAll(clubRepository.getProfilePostsPager(userId, profileUserID, page = page))
        return allPosts.toList()
    }

    suspend fun loadMore(userId: Int, profileUserID: Int, scrollType: Int): List<Post> {
        val page = if (scrollType >= 0) {
            1
        } else {
            lastPage += 1
            lastPage
        }
        allPosts.addAll(clubRepository.getProfilePostsPager(userId, profileUserID, page = page))
        return allPosts.sortedByDescending { it.createdTime }.toList()
    }

    private suspend fun getSavedPostsIds(): Flow<List<Int>> {
        return clubRepository.getSavedPostedIds()
    }

}