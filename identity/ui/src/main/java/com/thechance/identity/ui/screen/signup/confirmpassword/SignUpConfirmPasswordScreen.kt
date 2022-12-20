package com.thechance.identity.ui.screen.signup.confirmpassword

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.signup.name.navigateToSignupNames
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState


@Composable
fun SignUpConfirmPasswordScreen(
    navController: NavController,
    viewModel: SignupViewModel
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
        onClickBack = { navController.navigateUp() },
        onNavigate = { navController.navigateToLogInUserName() }
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
    onClickBack: () -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BackButton(onClick = onClickBack)

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = 8.dp)
        )

        SpacerVertical8()
        EmailDescriptionText(
            email = state.email,
        )

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.your_password),
            style = Typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        PasswordInputText(
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = state.password,
            onTextChange = onChangePassword,
            match = onConfirmCorrect.invoke()
        )

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.confirm_password),
            style = Typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 8.dp)
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

        NavigateToAnotherScreen(
            hintText = R.string.navigate_to_login,
            navigateText = R.string.log_in,
            onNavigate = onNavigate
        )
        SpacerVertical24()
    }
}