package com.devfalah.usecases

import com.devfalah.entities.Search
import com.devfalah.usecases.ClubRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: ClubRepository
) {

    suspend operator fun invoke(userId: Int, keyword: String): Search {
        return repository.search(userId, keyword)
    }
}