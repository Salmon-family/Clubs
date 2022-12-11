package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomePostUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private var lastPage = 1
    private val userGroupsIds = mutableListOf<Int>()

    suspend operator fun invoke(userId: Int) {
        userGroupsIds.addAll(getUserGroups(userId))
    }

    suspend fun loadData(userId: Int, scrollType: Int): Flow<List<Post>> {
        val page = if (scrollType >= 0) {
            1
        } else {
            lastPage += 1
            lastPage
        }
        return flow {
            emit(getHomePosts(userId, page))
//            userGroupsIds.forEachIndexed { index, groupId ->
//                //should change to group ID
//                emit(getUserGroupsPosts(index + 1))
//            }
        }
    }

    private suspend fun getUserGroups(userId: Int): List<Int> {
        return clubRepository.getGroupsIDsThatUserMemberOF(userId)
    }

    private suspend fun getHomePosts(userId: Int, page: Int): List<Post> {
        return clubRepository.getUserHomePosts(userId, page)
    }

    private suspend fun getUserGroupsPosts(groupId: Int): List<Post> {
        return clubRepository.getProfilePosts(groupId, groupId)
    }

}