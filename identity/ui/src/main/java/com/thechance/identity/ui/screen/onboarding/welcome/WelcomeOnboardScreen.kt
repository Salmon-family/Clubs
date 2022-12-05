package com.thechance.identity.ui.screen.onboarding.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.ClubText
import com.thechance.identity.ui.composable.AuthText
import com.thechance.identity.ui.composable.WelcomeOnBoardingBoxOfParallelogramShape
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightTernaryBlackColor
import com.thechance.identity.ui.theme.LightTernaryBrandColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.ui.screen.onboarding.pager.navigateToOnBoardingPager

@Composable
fun WelcomeOnboardScreen(
    navController: NavController,
) {
    WelcomeOnboardContent {
        navController.navigateToOnBoardingPager()
    }
}

@Composable
fun WelcomeOnboardContent(
    onClickNextScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightTernaryBrandColor)
    ) {

        Spacer(modifier = Modifier.weight(1f))
        WelcomeOnBoardingBoxOfParallelogramShape()

        SpacerVertical(height = 60.dp)
        ClubText()

        SpacerVertical8()
        AuthText(
            text = stringResource(id = R.string.have_fun),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 24.dp)
        )

        SpacerVertical8()
        AuthText(
            text = stringResource(id = R.string.onboard_body),
            style = Typography.body1,
            color = LightTernaryBlackColor,
            Modifier.padding(start = 24.dp)
        )

        SpacerVertical(height = 20.dp)
        AuthButton(
            onClick = onClickNextScreen,
            text = stringResource(id = R.string.lets_do),
            buttonModifier = Modifier
                .wrapContentSize()
                .padding(start = 24.dp),
            textModifier = Modifier
                .padding(vertical = 8.dp, horizontal = 24.dp)
        )
        SpacerVertical16()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWelcomeOnboard() {
    WelcomeOnboardContent({})
}