package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import com.devfalah.usecases.util.Constants.HOME_GROUP_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.roundToInt

class GetHomeThreadsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private val savedPosts: MutableList<Int> = mutableListOf()
    private var userId = -1
    private var postsCount = 0

    suspend operator fun invoke(): Flow<List<Post>> {
        userId = clubRepository.getUserId()
        val homePosts = clubRepository.getHomePosts().map {
            postsCount = it.size
            it.map { post ->
                if (post.id in savedPosts) {
                    post.copy(isSaved = true, isMyPost = userId == post.publisherId)
                } else {
                    post.copy(isMyPost = userId == post.publisherId)
                }
            }
        }
        return homePosts
    }

    suspend fun loadData(): Boolean {
        val page = if (postsCount == 0) {
            1
        } else {
            (postsCount / 10.0).roundToInt() + 1
        }
        return refreshData(page)
    }

    private suspend fun refreshData(page: Int): Boolean {
        if (userId == -1) {
            userId = clubRepository.getUserId()
        }
        val homePosts = clubRepository.getUserHomePosts(userId, page).map { post ->
            if (post.id in savedPosts) {
                post.copy(isSaved = true, isMyPost = userId == post.publisherId)
            } else {
                post.copy(isMyPost = userId == post.publisherId)
            }
        }
        clubRepository.addHomePosts(homePosts)
        return true
    }

    private suspend fun getSavedPostsIds() {
        return clubRepository.getSavedPostedIds(HOME_GROUP_ID).collect {
            savedPosts.addAll(it)
        }
    }

}