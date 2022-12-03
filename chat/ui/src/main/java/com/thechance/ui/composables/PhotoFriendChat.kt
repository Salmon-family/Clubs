package com.thechance.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

@Composable
fun PhotoFriendChat (
    url:String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(model = url),
        modifier = modifier
            .clip(CircleShape)
            .size(56.dp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}