package com.thechance.identity.ui.screen.login.password

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.composable.NavigateToAnotherScreen
import com.thechance.identity.ui.composable.PasswordInputText
import com.thechance.identity.ui.screen.home.navigateToHome
import com.thechance.identity.ui.screen.signup.email.navigateToSignupEmail
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.login.LoginUIState
import com.thechance.identity.viewmodel.login.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LogInPasswordScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()

    LogInPasswordContent(
        state = state,
        onChangePassword = viewModel::onChangePassword,
        onValidate = viewModel::onValidatePassword,
        onLogin = {
            if (!state.isSuccess) {
                Toast.makeText(context, state.isError, Toast.LENGTH_SHORT).show()
            } else {
                navController.navigateToHome()
                Toast.makeText(
                    context,
                    context.getString(R.string.success_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
        onClickBack = { navController.navigateUp() },
        onNavigate = { navController.navigateToSignupEmail() }
    )

    LaunchedEffect(key1 = viewModel) {
        viewModel.onLogin()
    }
}

@Composable
fun LogInPasswordContent(
    state: LoginUIState,
    onChangePassword: (String) -> Unit,
    onValidate: () -> Boolean,
    onLogin: () -> Unit,
    onClickBack: () -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

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
            text = stringResource(id = R.string.your_password),
            style = Typography.subtitle2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        PasswordInputText(
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = state.password,
            onTextChange = onChangePassword
        )

        SpacerVertical24()
        AuthButton(
            onClick = onLogin,
            isEnabled = onValidate.invoke(),
            text = stringResource(id = R.string.log_in),
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
fun LoginPasswordPreview() {

    LogInPasswordContent(
        state = LoginUIState(),
        onChangePassword = {},
        onValidate = { false },
        onLogin = {},
        onNavigate = {},
        onClickBack = {}
    )
}