package com.devfalah.ui.image

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.ZoomableImage
import com.devfalah.ui.util.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ImageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageUrl = intent.extras?.getString(Constants.IMAGE_URL_KEY).toString()

        setContent {
            val systemUi = rememberSystemUiController()
            systemUi.setStatusBarColor(Color.Black)

            ZoomableImage(
                rememberAsyncImagePainter(
                    model = imageUrl,
                    error = painterResource(id = R.drawable.info_circle)
                )
            )
        }
    }

}