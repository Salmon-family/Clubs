package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R

@Composable
fun ErrorEmpty(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        HeightSpacer24()

        Card(
            modifier = modifier.alpha(1f),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Icon(
                modifier = Modifier.padding(20.dp),
                painter = painterResource(id = R.drawable.info_circle),
                contentDescription = "Info",
                tint = Color.Red
            )
        }
    }
}