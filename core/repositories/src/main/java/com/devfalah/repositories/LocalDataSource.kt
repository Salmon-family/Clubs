package com.devfalah.repositories

import com.devfalah.repositories.models.post.PostHome
import com.devfalah.repositories.models.post.PostHomeDto
import com.devfalah.repositories.models.post.PostLocalDto
import kotlinx.coroutines.flow.Flow

interface CoreLocalDataSource {

    suspend fun insertPost(post: PostLocalDto)

    suspend fun getPosts(): Flow<List<PostLocalDto>>

    suspend fun getPostsIds(groupId: Int): Flow<List<Int>>

    suspend fun isPostFound(postId: Int): Boolean

    suspend fun deletePostById(postId: Int)

    //region Home
    suspend fun insertHomePosts(posts: List<PostHomeDto>)

    suspend fun getHomePosts(): Flow<List<PostHome>>

    suspend fun insertHomePost(post: PostHomeDto)

    suspend fun clearHomePosts()

    suspend fun deleteHomePosts(postId: Int)

    suspend fun getTotalHomePost(): Int
    //endregion

}