package com.thechance.identity.ui.screen.onboarding.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.shape.ParallelogramShape

@Composable
fun WelcomeOnBoardingBoxOfParallelogramShape() {
    Box(
        modifier = Modifier
            .height(292.dp)
            .background(
                shape = ParallelogramShape(72f),
                color = Color.White
            ),
        contentAlignment = Alignment.Center
    ) {
        WelcomeOnBoardImage(
            painter = painterResource(id = R.drawable.welcome),
            description = stringResource(id = R.string.welcome)
        )
    }
}

@Composable
private fun WelcomeOnBoardImage(painter: Painter, description: String? = null) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = description,
            modifier = Modifier
                .height(292.dp)
                .aspectRatio(1f / 1f)
        )
    }
}