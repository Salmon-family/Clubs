package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CircleImage(
    painter: Painter,
    size: Int = 48,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = "profile user",
        modifier = modifier
            .clip(shape = CircleShape)
            .size(size.dp),
        contentScale = ContentScale.Crop
    )
}