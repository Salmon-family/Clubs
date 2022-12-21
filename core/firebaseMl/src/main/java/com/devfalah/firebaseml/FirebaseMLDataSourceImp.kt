package com.devfalah.firebaseml

import com.devfalah.repositories.FirebaseMLDataSource
import com.devfalah.repositories.models.ImageLabelData
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class FirebaseMLDataSourceImp @Inject constructor(
    private val imageAnalysis: ImageAnalysis,
) : FirebaseMLDataSource {
    override fun analyzeImage(file: File): List<ImageLabelData> {
        return imageAnalysis.checkImage(file).map { ImageLabelData(it.text, it.confidence) }
    }
}