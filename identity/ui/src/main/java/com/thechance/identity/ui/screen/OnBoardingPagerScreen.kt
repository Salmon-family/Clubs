package com.thechance.identity.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.ButtonComposable
import com.thechance.identity.ui.composable.FooterOnBoarding
import com.thechance.identity.ui.composable.ViewPagerSlider
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightTernaryBlackColor


@Preview(showSystemUi = true)
@Composable
fun OnBoardingPagerScreen() {
    Column {
        Box(Modifier.weight(1f)) {
            ViewPagerSlider()
        }
        SpacerVertical16()
        ButtonComposable(
            onClick = {},
            text = stringResource(id = R.string.login),
        )
        SpacerVertical16()
        ButtonComposable(
            onClick = {},
            text = stringResource(id = R.string.register),
            buttonColor = LightCardColor,
            textColor = LightPrimaryBlackColor
        )
        SpacerVertical16()
        FooterOnBoarding(
            text1 = stringResource(R.string.footer_first_text),
            color1 = LightTernaryBlackColor,
            text2 = stringResource(id = R.string.footer_second_text),
            color2 = LightPrimaryBrandColor
        )
        SpacerVertical16()
    }
}