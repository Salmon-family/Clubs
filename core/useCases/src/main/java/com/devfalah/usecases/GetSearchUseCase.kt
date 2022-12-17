package com.devfalah.usecases

import com.devfalah.entities.SearchResult
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int, keyword: String): SearchResult {
        return clubRepository.getSearch(userID = userId, keyword = keyword)
    }

}