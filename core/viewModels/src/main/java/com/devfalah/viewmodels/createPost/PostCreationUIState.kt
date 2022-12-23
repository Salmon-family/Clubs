package com.devfalah.viewmodels.createPost

import android.graphics.Bitmap
import android.net.Uri
import java.io.File

data class PostCreationUIState(
    val id: Int = 0,
    val postContent: String = "",
    val privacy: Int = 2,
    val imageFile: File? = null,
    val imageBitmap: Bitmap? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)