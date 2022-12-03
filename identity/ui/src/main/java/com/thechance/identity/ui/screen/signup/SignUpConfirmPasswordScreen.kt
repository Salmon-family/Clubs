package com.thechance.identity.ui.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography


@Composable
fun SignUpConfirmPasswordScreen(
    navController: NavController
) {
    SignUpConfirmPasswordContent(
        onClickSignupInformationScreen = {
            navController.navigate("signupUserInformationScreen")
        },
        onClickBack = { navController.navigateUp() }
    )
}

@Composable
fun SignUpConfirmPasswordContent(
    onClickSignupInformationScreen: () -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BackButtonComposable { onClickBack }

        SpacerVertical(height = 36.dp)
        TextComposable(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 8.dp)
        EmailTextComposable(
            text1 = "Using ",
            color1 = LightSecondaryBlackColor,
            text2 = "motu.uiux@gmail.com",
            color2 = LightPrimaryBrandColor,
            text3 = " to login"
        )

        SpacerVertical(height = 24.dp)
        TextComposable(
            text = stringResource(id = R.string.your_password),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputTextComposable(
            type = KeyboardType.Password,
            image = R.drawable.ic_close,
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = "",
            onTextChange = {}
        ) {

        }

        SpacerVertical(height = 24.dp)
        TextComposable(
            text = stringResource(id = R.string.confirm_password),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputTextComposable(
            type = KeyboardType.Password,
            image = R.drawable.ic_close,
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = "",
            onTextChange = {}
        ) {

        }

        SpacerVertical(height = 24.dp)
        ButtonComposable(
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            onClick = onClickSignupInformationScreen,
            text = stringResource(id = R.string.continue_label)
        )
    }
}