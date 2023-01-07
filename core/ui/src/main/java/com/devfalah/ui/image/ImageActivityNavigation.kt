package com.devfalah.ui.image

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.devfalah.ui.util.Constants

fun navigateToImageScreen(context: Context, imageUrl: String) {
    val intent = Intent(context, ImageActivity::class.java)
    intent.putExtra(Constants.IMAGE_URL_KEY, imageUrl)
    ContextCompat.startActivity(context, intent, null)
}