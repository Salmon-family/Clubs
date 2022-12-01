package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R

@Composable
fun BackButtonComposable(
    onClick: () -> Unit
){
    Image(
        painter = painterResource(id = R.drawable.ic_back_arrow),
        contentDescription = null,
        modifier = Modifier.clickable { onClick }
    )
}