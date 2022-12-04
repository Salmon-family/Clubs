package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PhotoFriendChat (
    url: String,
    filterQuality: FilterQuality = FilterQuality.High,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberAsyncImagePainter(model = url, filterQuality = filterQuality),
        modifier = modifier
            .clip(CircleShape)
            .size(56.dp),
        contentDescription = null,
        contentScale = contentScale,
    )
}