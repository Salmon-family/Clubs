package com.devfalah.usecases.language

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(
    private val repository: ClubRepository
) {

    suspend operator fun invoke(language: String) {
        return repository.saveLanguage(language)
    }
}