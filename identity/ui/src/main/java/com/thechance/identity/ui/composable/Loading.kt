package com.thechance.identity.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.spacer.SpacerVertical24

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerVertical24()

        Box(
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colors.surface
                )
                .alpha(1f),
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}