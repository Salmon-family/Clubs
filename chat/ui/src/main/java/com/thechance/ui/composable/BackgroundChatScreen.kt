package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.thechance.ui.R


@Composable
fun BackgroundChatScreen() {
    if (isSystemInDarkTheme()){
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface))
    }
    else {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background_chat_screen2),
            contentDescription = "background chat screen",
            contentScale = ContentScale.Crop,
        )
    }

}