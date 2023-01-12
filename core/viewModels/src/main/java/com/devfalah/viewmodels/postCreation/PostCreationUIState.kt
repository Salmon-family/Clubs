package com.devfalah.viewmodels.postCreation

import android.graphics.Bitmap
import java.io.File

data class PostCreationUIState(
    val clubId: Int = 0,
    val isClub: Boolean = false,
    val postContent: String = "",
    val privacy: Int = 2,
    val imageFile: File? = null,
    val imageBitmap: Bitmap? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)

fun PostCreationUIState.isEnabled(): Boolean {
    return !isLoading && (postContent.trim().isNotEmpty() || imageFile != null)
}