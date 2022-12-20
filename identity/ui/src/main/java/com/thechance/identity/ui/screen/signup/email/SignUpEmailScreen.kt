package com.thechance.identity.ui.screen.signup.email

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.composable.InputText
import com.thechance.identity.ui.composable.NavigateToAnotherScreen
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.signup.confirmpassword.navigateToSignupConfirmPassword
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState


@Composable
fun SignUpEmailScreen(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val state by viewModel.uiState.collectAsState()
    SignUpEmailContent(
        state = state,
        onChangeEmail = viewModel::onChangeEmail,
        onValidateEmailType = viewModel::checkIfGmailOrAnotherType,
        onClickPasswordScreen = { navController.navigateToSignupConfirmPassword() },
        onClickBack = { navController.navigateUp() },
        onNavigate = {navController.navigateToLogInUserName()}
    )
}

@Composable
private fun SignUpEmailContent(
    state: UserUIState,
    onChangeEmail: (String) -> Unit,
    onClickPasswordScreen: () -> Unit,
    onValidateEmailType: (String) -> Boolean,
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

        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.email),
            style = Typography.subtitle2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        InputText(
            type = KeyboardType.Email,
            placeHolder = stringResource(id = R.string.email_place_holder),
            text = state.email,
            onTextChange = onChangeEmail
        )
        Spacer(Modifier.height(24.dp))
        AuthButton(
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            isEnabled = onValidateEmailType.invoke(state.email),
            onClick = onClickPasswordScreen,
            text = stringResource(id = R.string.continue_label)
        )

        NavigateToAnotherScreen(
            hintText = R.string.navigate_to_login,
            navigateText = R.string.log_in,
            onNavigate = onNavigate
        )
    }
}