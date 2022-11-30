package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardImage(painter: Painter, description:String = ""){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = description,
            modifier = Modifier
                .width(220.dp)
                .aspectRatio(3f / 4f)
                .padding(top = 140.dp)
        )
    }
}