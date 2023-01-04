package com.devfalah.usecases.language

import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class UpdateLanguageUseCase @Inject constructor(
    private val repository: ClubRepository
) {
    suspend operator fun invoke(defaultLanguage: String): String {
        val lang = getSavedAppLanguage()
        return if (lang.isNullOrEmpty()) {
            repository.saveLanguage(defaultLanguage)
            defaultLanguage
        } else {
            lang
        }
    }

    private suspend fun getSavedAppLanguage(): String? {
        return repository.getLanguage()
    }

    suspend fun changeLanguage(language: String) {
        repository.saveLanguage(language)

    }

    suspend fun getAppLanguage(): String {
        return getSavedAppLanguage() ?: "en"
    }


}