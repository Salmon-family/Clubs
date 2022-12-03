package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CircleImage(
    imageUrl: String
) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = "PROFILE",
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(72.dp),
        contentScale = ContentScale.Crop
    )
}