package com.thechance.identity.ui.screen.login.password

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.home.navigateToHome
import com.thechance.identity.ui.screen.signup.email.navigateToSignupEmail
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.login.LoginUIState
import com.thechance.identity.viewmodel.login.LoginViewModel

@Composable
fun LogInPasswordScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    LogInPasswordContent(
        state = state,
        onChangePassword = viewModel::onChangePassword,
        onValidate = viewModel::onValidatePassword,
        onLogin = viewModel::onLogin,
        onClickBack = { navController.navigateUp() },
        onNavigate = { navController.navigateToSignupEmail() },
        navController = navController
    )

}

private fun checkLogin(state: LoginUIState, context: Context, navController: NavController) {
    if (!state.isSuccess) {
        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
    } else {
        navController.navigateToHome()
        Toast.makeText(
            context, context.getString(R.string.success_message), Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun LogInPasswordContent(
    state: LoginUIState,
    onChangePassword: (String) -> Unit,
    onValidate: () -> Boolean,
    onLogin: () -> Unit,
    onClickBack: () -> Unit,
    onNavigate: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
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

            if (state.errorMessage.isNotEmpty()) {
                Error(
                    errorMessage = state.errorMessage,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            NavigateToAnotherScreen(
                hintText = R.string.navigate_to_signup,
                navigateText = R.string.sign_up,
                onNavigate = onNavigate
            )
        }

        if (state.isLoading) {
            Loading()
        }
    }

    LaunchedEffect(key1 = state.isLoading) {
        if (state.isSuccess) {
            navController.navigateToHome()
            Toast.makeText(context, R.string.success_message, Toast.LENGTH_SHORT).show()
        }
    }
}