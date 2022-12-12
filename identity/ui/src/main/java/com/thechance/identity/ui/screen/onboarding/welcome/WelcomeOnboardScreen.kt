package com.thechance.identity.ui.screen.onboarding.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.ClubText
import com.thechance.identity.ui.screen.onboarding.composable.WelcomeOnBoardImage
import com.thechance.identity.ui.screen.onboarding.pager.navigateToOnBoardingPager
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightTernaryBlackColor
import com.thechance.identity.ui.theme.Typography

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
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.weight(1f))
        WelcomeOnBoardImage(
            painter = painterResource(id = R.drawable.welcome),
            description = stringResource(id = R.string.welcome)
        )

        Spacer(modifier = Modifier.weight(1f))
        ClubText()

        SpacerVertical8()
        Text(
            text = stringResource(id = R.string.have_fun),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        SpacerVertical8()
        Text(
            text = stringResource(id = R.string.onboard_body),
            style = Typography.body1,
            color = LightTernaryBlackColor,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        SpacerVertical16()
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
    WelcomeOnboardContent {}
}