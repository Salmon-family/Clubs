package com.thechance.ui.screens.friends

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
    modifier: Modifier = Modifier,
    painter: Painter,
    size: Int = 48,
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .clip(shape = CircleShape)
            .size(size.dp),
        contentScale = ContentScale.Crop
    )
}