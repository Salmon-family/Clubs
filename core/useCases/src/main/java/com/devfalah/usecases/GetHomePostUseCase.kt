package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private var page = 1
    private val userGroupsIds = mutableListOf<Int>()
    private lateinit var savedPosts: List<Int>

    suspend operator fun invoke(userId: Int) {
        getSavedPostsIds()
        userGroupsIds.addAll(getUserGroups(userId))
    }

    suspend fun loadData(userId: Int, scrollType: Int): Flow<List<Post>> {
        val homePosts = getHomePosts(userId, page).map { post ->
            if (post.id in savedPosts) {
                post.copy(isSaved = true)
            } else {
                post
            }
        }

        if (homePosts.isNotEmpty()) {
            page += 1
        }
        return flow {
            emit(homePosts)
//            userGroupsIds.forEachIndexed { index, groupId ->
//                //should change to group ID
//                emit(getUserGroupsPosts(index + 1))
//            }
        }
    }

    private suspend fun getUserGroups(userId: Int): List<Int> {
        return clubRepository.getGroupsIDsThatUserMemberOF(userId).map { it.id }
    }

    private suspend fun getHomePosts(userId: Int, page: Int): List<Post> {
        return clubRepository.getUserHomePosts(userId, page)
    }

    private suspend fun getUserGroupsPosts(groupId: Int): List<Post> {
        return clubRepository.getProfilePosts(groupId, groupId)
    }

    private suspend fun getSavedPostsIds() {
        return clubRepository.getSavedPostedIds().collect {
            savedPosts = it
        }
    }

}