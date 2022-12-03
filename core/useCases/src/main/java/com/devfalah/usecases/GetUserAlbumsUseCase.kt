package com.devfalah.usecases

import com.devfalah.entities.Album
import javax.inject.Inject

class GetUserAlbumsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {

    suspend operator fun invoke(userId: Int): List<Album> {
        return clubRepository.getUserAlbums(userId, userId)
    }
}