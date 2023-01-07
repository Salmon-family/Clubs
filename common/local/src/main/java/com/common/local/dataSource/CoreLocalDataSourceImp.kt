package com.common.local.dataSource


import com.common.local.daos.ClubDao
import com.devfalah.repositories.CoreLocalDataSource
import com.devfalah.repositories.models.post.PostHome
import com.devfalah.repositories.models.post.PostHomeDto
import com.devfalah.repositories.models.post.PostLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoreLocalDataSourceImp @Inject constructor(
    private val clubDao: ClubDao
) : CoreLocalDataSource {

    override suspend fun insertPost(post: PostLocalDto) {
        clubDao.insertPost(post)
    }

    override suspend fun getPosts(): Flow<List<PostLocalDto>> {
        return clubDao.getPosts()
    }

    override suspend fun getPostsIds(groupId: Int): Flow<List<Int>> {
        return clubDao.getPostsIds(groupId)
    }

    override suspend fun isPostFound(postId: Int): Boolean {
        return clubDao.getPostWithId(postId) == 1
    }

    override suspend fun deletePostById(postId: Int) {
        clubDao.deletePostById(postId)
    }

    override suspend fun insertHomePosts(posts: List<PostHomeDto>) {
        clubDao.insertPosts(posts)
    }

    override suspend fun insertHomePost(post: PostHomeDto) {
        clubDao.insertPost(post)
    }

    override suspend fun getHomePosts(): Flow<List<PostHome>> {
        return clubDao.getHomePosts()
    }

    override suspend fun deleteHomePosts(postId: Int) {
        clubDao.deleteHomePost(postId = postId)
    }

    override suspend fun clearHomePosts() {
        clubDao.clearHomePosts()
    }
}