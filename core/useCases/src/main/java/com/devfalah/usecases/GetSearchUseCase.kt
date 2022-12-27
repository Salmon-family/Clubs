package com.devfalah.usecases

import com.devfalah.entities.SearchResult
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(keyword: String): SearchResult {
        return if (keyword.isNotEmpty()) {
            clubRepository.getSearch(userID = clubRepository.getUserId(), keyword = keyword)
        } else {
            SearchResult(emptyList(), emptyList())
        }
    }

}