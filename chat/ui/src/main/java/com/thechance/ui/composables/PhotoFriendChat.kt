package com.thechance.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

@Composable
fun PhotoFriendChat (
    chatUiState: ChatUiState,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(model = chatUiState.icon),
        modifier = modifier, contentDescription = null, contentScale = ContentScale.Crop
    )
}