package com.devfalah.ui.composable

import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import moe.tlaster.zoomable.Zoomable
import moe.tlaster.zoomable.rememberZoomableState

@Composable
fun ZoomableImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    @FloatRange(from = 0.0) minScale: Float = 1f,
    @FloatRange(from = 0.0) maxScale: Float = Float.MAX_VALUE,
) {
    val state = rememberZoomableState(
        minScale = minScale,
        maxScale = maxScale
    )

    Zoomable(
        state = state,
        doubleTapScale = {
            if (state.scale > state.minScale) state.minScale else 2f
        },
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}