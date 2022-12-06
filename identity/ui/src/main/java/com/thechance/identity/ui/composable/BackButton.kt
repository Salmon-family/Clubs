package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.thechance.identity.ui.R

@Composable
fun BackButton(
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}