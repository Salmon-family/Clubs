package com.devfalah.usecases.language

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val repository: ClubRepository
) {

    operator fun invoke(): String? {
        return repository.getLanguage()
    }
}