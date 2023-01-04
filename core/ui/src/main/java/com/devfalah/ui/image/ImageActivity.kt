package com.devfalah.ui.image

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ImageActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageUrl = intent.extras?.getString("IMAGE_URL").toString()

        setContent {
            val systemUi = rememberSystemUiController()
            systemUi.setStatusBarColor(Color.Black)
            systemUi.isSystemBarsVisible = false
            systemUi.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
            ZoomableImage(imageUrl = imageUrl)
        }
    }


    @Composable
    private fun ZoomableImage(
        modifier: Modifier = Modifier,
        imageUrl: String,
        contentScale: ContentScale = ContentScale.Crop,

        ) {
        var scale by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Offset.Zero) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "A Content description",
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = if (scale > 1f) offset.x else 0f,
                        translationY = if (scale > 1f) offset.y else 0f
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures(
                            onGesture = { _, pan: Offset, zoom: Float, _ ->
                                offset += pan
                                scale = (scale * zoom).coerceIn(1f, 2f)
                            }
                        )
                    },
                contentScale = contentScale
            )
        }
    }
}