package com.thechance.identity.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thechance.identity.ui.composable.ViewPagerSlider


@Preview(showSystemUi = true)
@Composable
fun OnBoardingPagerScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ViewPagerSlider()
    }
}