package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private var lastPage = 1

    suspend operator fun invoke(userId: Int, profileUserID: Int): List<Post> {
        return clubRepository.getProfilePostsPager(userId, profileUserID, page = page)
    }

    suspend fun loadMore(userId: Int, profileUserID: Int, scrollType: Int): List<Post> {
        val page = if (scrollType >= 0) {
            1
        } else {
            lastPage += 1
            lastPage
        }
        return clubRepository.getProfilePostsPager(userId, profileUserID, page = page)
    }
}