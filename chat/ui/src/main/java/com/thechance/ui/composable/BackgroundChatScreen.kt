package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.thechance.ui.R


@Composable
fun BackgroundChatScreen() {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background_chat_screen),
        contentDescription = "background chat screen",
        contentScale = ContentScale.Crop,
    )


}