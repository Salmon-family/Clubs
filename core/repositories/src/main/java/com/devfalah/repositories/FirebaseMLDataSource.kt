package com.devfalah.repositories

import com.devfalah.repositories.models.ImageLabelData
import kotlinx.coroutines.flow.Flow
import java.io.File

interface FirebaseMLDataSource {
    suspend fun analyzeImage(file: File): List<ImageLabelData>
}