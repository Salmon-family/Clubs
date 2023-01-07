package com.thechance.identity.ui.screen.onboarding.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.onboarding.composable.ViewPagerSlider
import com.thechance.identity.ui.screen.signup.email.navigateToSignupEmail
import com.thechance.identity.ui.spacer.SpacerVertical16

@Composable
fun OnBoardingPagerScreen(
    navController: NavController
) {
    OnBoardingPagerContent({
        navController.navigateToSignupEmail()
    }, {
        navController.navigateToLogInUserName()
    })
}

@Composable
fun OnBoardingPagerContent(
    onClickSignUpScreen: () -> Unit,
    onClickLogInScreen: () -> Unit
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colors.background)
            .padding(vertical = 16.dp),

    ) {

        ViewPagerSlider(Modifier.weight(1f))

        SpacerVertical16()


        AuthButton(
            onClick = onClickSignUpScreen,
            text = stringResource(id = R.string.sign_up),
        )

        SpacerVertical16()
        AuthButton(
            onClick = onClickLogInScreen,
            text = stringResource(id = R.string.login),
            buttonColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.primaryVariant
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun OnBoardingPagerPreview() {
    val navController = rememberNavController()
    OnBoardingPagerScreen(navController)
}