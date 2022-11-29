package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconComposable(
    icon: Int
) {
    Image(
        painter = painterResource(
            id = icon
        ),
        contentDescription = null,
        modifier = Modifier.padding(8.dp)
    )
}