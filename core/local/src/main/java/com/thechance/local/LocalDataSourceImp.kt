package com.thechance.local

import com.devfalah.repositories.LocalDataSource
import com.devfalah.repositories.models.PostLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val clubDao: ClubDao
) : LocalDataSource {

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