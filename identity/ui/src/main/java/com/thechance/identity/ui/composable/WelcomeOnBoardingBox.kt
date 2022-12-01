package com.thechance.identity.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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