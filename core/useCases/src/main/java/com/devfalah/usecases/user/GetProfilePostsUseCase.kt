package com.devfalah.usecases.user

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import com.devfalah.usecases.util.Constants.HOME_GROUP_ID
import javax.inject.Inject
import kotlin.properties.Delegates

class GetProfilePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    private var page = 1
    private var userId by Delegates.notNull<Int>()
    private lateinit var savedPosts: List<Int>

    init {
        userId = clubRepository.getUserId()
    }

    suspend operator fun invoke(profileUserID: Int): List<Post> {
        getSavedPostsIds()

        val profileId = if (profileUserID == -1) {
            userId
        } else {
            profileUserID
        }

        return clubRepository.getProfilePostsPager(profileId, profileUserID, page = page)
    }

    suspend fun loadMore(profileUserID: Int, isRestart: Boolean): List<Post> {
        val profileId = if (profileUserID == -1) { userId } else { profileUserID }
        page = getPageNumber(isRestart)
        val posts = clubRepository.getProfilePostsPager(userId, profileId, page = page)
        return if (posts.isNotEmpty()) {
            page += 1
            posts.map { post ->
                if (post.id in savedPosts) {
                    post.copy(isSaved = true, isMyPost = userId == profileId)
                } else {
                    post.copy(isMyPost = userId == profileId)
                }
            }
        } else {
            emptyList()
        }
    }

    private suspend fun getSavedPostsIds() {
        return clubRepository.getSavedPostedIds(HOME_GROUP_ID).collect {
            savedPosts = it
        }
    }


    private fun getPageNumber(isRestart: Boolean): Int {
        return if (isRestart) {
            1
        } else {
            page
        }
    }
}