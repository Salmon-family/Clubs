package com.thechance.identity.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.ButtonComposable
import com.thechance.identity.ui.composable.ViewPagerSlider
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.WhiteColor


@Preview(showSystemUi = true)
@Composable
fun OnBoardingPagerScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SpacerVertical16()
        ViewPagerSlider()
        SpacerVertical16()
        ButtonComposable(
            onClick = {},
            text = stringResource(id = R.string.login),
            buttonColor = LightPrimaryBrandColor,
            textColor = WhiteColor
        )
        SpacerVertical16()
        ButtonComposable(
            onClick = {},
            text = stringResource(id = R.string.register),
            buttonColor = LightCardColor,
            textColor = LightPrimaryBlackColor
        )
    }
}