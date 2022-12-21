package com.devfalah.usecases

import com.devfalah.usecases.repository.ClubRepository
import java.io.File
import javax.inject.Inject

class AnalyzeImageUseCase @Inject constructor(
    private val repository: ClubRepository,
) {
    suspend operator fun invoke(file: File) {
        try {
            val value = repository.analyzeImage(file)
            println("DEVFALAH $value")


        } catch (e: Throwable) {
            println("DEVFALAH ${e.message}")
        }
    }
}