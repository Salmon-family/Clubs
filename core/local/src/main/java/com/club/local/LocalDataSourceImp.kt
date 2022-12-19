 package com.club.local

import com.devfalah.repositories.ClubLocalDataSource
import com.devfalah.repositories.models.PostLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClubLocalDataSourceImp @Inject constructor(
    private val clubDao: ClubDao
) : ClubLocalDataSource {

    override suspend fun insertPost(post: PostLocalDto) {
        clubDao.insertPost(post)
    }

    override suspend fun getPosts(): Flow<List<PostLocalDto>> {
        return clubDao.getPosts()
    }

    override suspend fun getPostsIds(): Flow<List<Int>> {
        return clubDao.getPostsIds()
    }

    override suspend fun isPostFound(postId: Int): Boolean {
        return clubDao.getPostWithId(postId) == 1
    }

    override suspend fun deletePostById(postId: Int) {
        clubDao.deletePostById(postId)
    }


}