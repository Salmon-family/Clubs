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

    suspend operator fun invoke(): Flow<List<Post>> {
        userId = getUserId()
        loadData(FIRST_TIME)
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
        return refreshData(page)
    }

    private suspend fun refreshData(page: Int): Boolean {
        if (userId == ERROR_USER) {
            userId = getUserId()
        }
        val homePosts = clubRepository.getUserHomePosts(userId, page).map { post ->
            post.copy(isMyPost = userId == post.publisherId)
        }
        clubRepository.addHomePosts(homePosts)
        return true
    }


    private fun getUserId(): Int {
        return clubRepository.getUserId()
    }
}