@file:Suppress("UNUSED_EXPRESSION")

package com.thechance.identity.ui.screen.login.username

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.login.password.navigateToLogInPassword
import com.thechance.identity.ui.screen.signup.email.navigateToSignupEmail
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.*
import com.thechance.identity.viewmodel.login.LoginUIState
import com.thechance.identity.viewmodel.login.LoginViewModel

@Composable
fun LogInUserNameScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val state by viewModel.uiState.collectAsState()
    LogInUserNameContent(
        state = state,
        onChangeUserName = viewModel::onChangeUserName,
        onClickContinue = { navController.navigateToLogInPassword() },
        onClickBack = { navController.navigateUp() },
        onNavigate = { navController.navigateToSignupEmail() }
    )
}

@Composable
private fun LogInUserNameContent(
    state: LoginUIState,
    onChangeUserName: (String) -> Unit,
    onClickContinue: () -> Unit,
    onClickBack: () -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
    ) {
        BackButton(onClick = onClickBack)

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.log_in),
            style = Typography.h1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.user_name),
            style = Typography.subtitle2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        InputText(
            type = KeyboardType.Text,
            placeHolder = stringResource(id = R.string.user_name_place_holder),
            text = state.userName,
            onTextChange = onChangeUserName
        )

        SpacerVertical24()
        AuthButton(
            onClick = onClickContinue,
            isEnabled = state.userName.isNotEmpty(),
            text = stringResource(id = R.string.continue_label),
            buttonModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        NavigateToAnotherScreen(
            hintText = R.string.navigate_to_signup,
            navigateText = R.string.sign_up,
            onNavigate = onNavigate
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LogInUserNamePreview() {
    LogInUserNameContent(
        state = LoginUIState(),
        onChangeUserName = {},
        onClickBack = {},
        onClickContinue = {},
        onNavigate = {}
    )
}

