package com.devfalah.usecases

import com.devfalah.entities.SearchResult
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(keyword: String, limit: Int = 100): SearchResult {
        return if (keyword.isNotEmpty()) {
            val result =
                clubRepository.getSearch(userID = clubRepository.getUserId(), keyword = keyword)
            result.copy(
                clubs = result.clubs.take(limit),
                users = result.users.take(limit),
                isMoreClubs = result.clubs.size > limit,
                isMoreUsers = result.users.size > limit
            )
        } else {
            SearchResult()
        }
    }

}