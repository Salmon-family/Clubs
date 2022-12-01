package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.model.SliderData

@Composable
fun OnBoardingImage(
    sliderData: SliderData
){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Fit,
        painter = painterResource(id = sliderData.image),
        contentDescription = null
    )
}