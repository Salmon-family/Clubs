package com.devfalah.usecases.language

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: ClubRepository
) {

    // should default device lang
    suspend operator fun invoke(): String {
        return repository.getLanguage() ?: "en"
    }
}