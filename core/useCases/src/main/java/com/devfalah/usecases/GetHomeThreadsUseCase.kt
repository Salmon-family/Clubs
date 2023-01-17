package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import com.devfalah.usecases.util.Constants.ERROR_USER
import com.devfalah.usecases.util.Constants.FIRST_PAGE
import com.devfalah.usecases.util.Constants.FIRST_TIME
import com.devfalah.usecases.util.Constants.MAX_ITEMS_PER_PAGE
import com.devfalah.usecases.util.Constants.SCROLL_UP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHomeThreadsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private var userId = -1
    private var currentPage = 1

    suspend operator fun invoke(): Flow<List<Post>> {
        userId = getUserId()
        try {
            loadData(FIRST_TIME)
        } catch (t: Throwable) {
            if (clubRepository.getTotalHomePost() == 0) {
                throw t
            } else {
                currentPage = 1
            }
        }
        return clubRepository.getHomePosts().map {
            it.map { post ->
                post.copy(isMyPost = userId == post.publisherId)
            }
        }
    }

    suspend fun loadData(scrollDirection: Int): Boolean {
        val postsCount = clubRepository.getTotalHomePost()
        val page =
            if (scrollDirection == SCROLL_UP || postsCount == 0 || FIRST_TIME == scrollDirection) {
                FIRST_PAGE
            } else {
                (postsCount / MAX_ITEMS_PER_PAGE) + 1
            }

        if (currentPage < page || scrollDirection == SCROLL_UP) {
            currentPage = page
        }
        return refreshData()
    }

    private suspend fun refreshData(): Boolean {
        if (userId == ERROR_USER) {
            userId = getUserId()
        }
        val homePosts = clubRepository.getUserHomePosts(userId, currentPage).map { post ->
            post.copy(isMyPost = userId == post.publisherId)
        }
        clubRepository.addHomePosts(homePosts)

        return if (homePosts.isNotEmpty()) {
            currentPage += 1
            true
        } else {
            false
        }
    }


    private fun getUserId(): Int {
        return clubRepository.getUserId()
    }
}