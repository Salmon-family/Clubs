package com.devfalah.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devfalah.repositories.models.WallPostDTO
import javax.inject.Inject
import kotlin.properties.Delegates

class ProfilePostData @Inject constructor(
    private val service: ClubService,
) : PagingSource<Int, WallPostDTO>() {

    private var viewerId by Delegates.notNull<Int>()
    private var ownerProfileId by Delegates.notNull<Int>()

    fun setData(viewerId: Int, ownerProfileId: Int) {
        this.viewerId = viewerId
        this.ownerProfileId = ownerProfileId
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallPostDTO> {
        val pageNumber = params.key ?: 1

        return try {
            val response =
                service.getAllWallPosts(page = pageNumber, userID = viewerId, friendID = viewerId)

            LoadResult.Page(
                data = response.body()?.payload?.posts ?: emptyList(),
                prevKey = null,
                nextKey = pageNumber.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, WallPostDTO>): Int? {
        return state.anchorPosition
    }
}