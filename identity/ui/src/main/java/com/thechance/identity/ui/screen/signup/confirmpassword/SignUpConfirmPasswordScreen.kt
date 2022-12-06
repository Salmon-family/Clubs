package com.thechance.identity.ui.screen.signup.confirmpassword

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.signup.name.navigateToSignupNames
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState


@Composable
fun SignUpConfirmPasswordScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SignUpConfirmPasswordContent(
        state = state,
        onConfirmCorrect = viewModel::onConfirmPassword,
        onValidate = viewModel::onValidatePassword,
        onChangePassword = viewModel::onChangePassword,
        onChangeConfirmPassword = viewModel::onChangeConfirmPassword,
        onClickSignupFirstNameScreen = {
            navController.navigateToSignupNames()
        },
        onClickBack = { navController.navigateUp() }
    )
}

@Composable
fun SignUpConfirmPasswordContent(
    state: UserUIState,
    onConfirmCorrect: () -> Boolean,
    onValidate: () -> Boolean,
    onChangePassword: (String) -> Unit,
    onChangeConfirmPassword: (String) -> Unit,
    onClickSignupFirstNameScreen: () -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BackButton(onClick = onClickBack)

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical8()
        EmailDescriptionText(
            text1 = stringResource(id = R.string.using),
            color1 = LightSecondaryBlackColor,
            text2 = stringResource(id = R.string.email_place_holder),
            color2 = LightPrimaryBrandColor,
            text3 = stringResource(id = R.string.to_login)
        )

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.your_password),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        PasswordInputText(
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = state.password,
            onTextChange = onChangePassword,
            match = onConfirmCorrect.invoke()
        )

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.confirm_password),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        PasswordInputText(
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = state.confirmPassword,
            onTextChange = onChangeConfirmPassword,
            match = onConfirmCorrect.invoke()
        )

        SpacerVertical24()
        AuthButton(
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            onClick = onClickSignupFirstNameScreen,
            isEnabled = onValidate.invoke(),
            text = stringResource(id = R.string.continue_label)
        )
    }
}