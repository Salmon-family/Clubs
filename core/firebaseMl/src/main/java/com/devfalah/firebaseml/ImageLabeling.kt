package com.devfalah.firebaseml

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.devfalah.repositories.models.ImageLabelData
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.File
import javax.inject.Inject


class ImageAnalysis @Inject constructor() {

    fun checkImage(file: File): List<ImageLabel> {

        val filePath = file.path
        val bitmap: Bitmap = BitmapFactory.decodeFile(filePath)
        val options = ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.7f)
            .build()
        val labeler = ImageLabeling.getClient(options)
        val imageLabels = labeler.process(bitmap, 0)

        return imageLabels.result
    }
}


