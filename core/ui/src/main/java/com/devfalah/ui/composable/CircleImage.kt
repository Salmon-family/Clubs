package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.theme.PurpleLight

@Composable
fun CircleImage(
    image: String,
    size: Int
){
    Image(
        painter = rememberAsyncImagePainter(model = image),
        contentDescription = null,
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(color = PurpleLight)
    )
}