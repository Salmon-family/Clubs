package com.devfalah.repositories

import com.devfalah.repositories.models.PostHomeDto
import com.devfalah.repositories.models.PostLocalDto
import kotlinx.coroutines.flow.Flow

interface CoreLocalDataSource {

    suspend fun insertPost(post: PostLocalDto)

    suspend fun getPosts(): Flow<List<PostLocalDto>>

    suspend fun getPostsIds(groupId: Int): Flow<List<Int>>

    suspend fun isPostFound(postId: Int): Boolean

    suspend fun deletePostById(postId: Int)

    //region Home
    suspend fun insertHomePosts(posts: List<PostHomeDto>)

    suspend fun getHomePosts(): Flow<List<PostHomeDto>>

    suspend fun insertHomePost(post: PostHomeDto)

    suspend fun clearHomePosts()
    //endregion

}