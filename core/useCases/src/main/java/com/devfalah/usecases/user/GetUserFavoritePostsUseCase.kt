package com.devfalah.usecases.user

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserFavoritePostsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    suspend operator fun invoke(): Flow<List<Post>> {
        return clubRepository.getSavedPosted()
    }
}