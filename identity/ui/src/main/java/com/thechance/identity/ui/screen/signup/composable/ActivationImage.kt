package com.thechance.identity.ui.screen.signup.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R

@Composable
fun ActivationImage(){
    Image(
        painter = painterResource(id = R.drawable.mobile_in_hand),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
            .aspectRatio(1f/1f)
            .padding(horizontal = 14.dp)
    )
}