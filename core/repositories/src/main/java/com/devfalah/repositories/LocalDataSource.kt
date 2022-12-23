package com.devfalah.repositories

import com.devfalah.repositories.models.PostLocalDto
import kotlinx.coroutines.flow.Flow

interface ClubLocalDataSource {

    suspend fun insertPost(post: PostLocalDto)

    suspend fun getPosts(): Flow<List<PostLocalDto>>

    suspend fun getPostsIds(groupId: Int): Flow<List<Int>>

    suspend fun isPostFound(postId: Int): Boolean

    suspend fun deletePostById(postId: Int)

}