package com.devfalah.firebaseml

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.File
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class ImageAnalysis @Inject constructor() {

    suspend fun checkImage(file: File): List<ImageLabel> {
        return suspendCoroutine { cont ->
            val filePath = file.path
            val bitmap: Bitmap = BitmapFactory.decodeFile(filePath)
            val options = ImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.7f)
                .build()
            val labeler = ImageLabeling.getClient(options)
            val imageLabels = labeler.process(bitmap, 0)

            imageLabels.addOnSuccessListener {
                cont.resume(it)
                Log.e("DEVFALAH", it.toString())
            }
        }
    }
}


