package com.thechance.identity.ui.screen.login.password

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.signup.email.navigateToSignupEmail
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.login.LoginUIState
import com.thechance.identity.viewmodel.login.LoginViewModel
import com.thechance.identity.viewmodel.login.isEnabled
import com.thechance.identity.viewmodel.utils.ErrorMessageType

@Composable
fun LogInPasswordScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LogInPasswordContent(
        state = state,
        onChangePassword = viewModel::onChangePassword,
        onValidate = viewModel::onValidatePassword,
        onLogin = viewModel::onLogin,
        onClickBack = { navController.navigateUp() },
        onNavigate = { navController.navigateToSignupEmail() },
    )

}

@Composable
fun LogInPasswordContent(
    state: LoginUIState,
    onChangePassword: (String) -> Unit,
    onValidate: () -> Boolean,
    onLogin: () -> Unit,
    onClickBack: () -> Unit,
    onNavigate: () -> Unit,
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

            BackButton(
                onClick = onClickBack, isEnabled = !state.isLoading,
            )

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
                onTextChange = onChangePassword,
            )

            SpacerVertical24()
            AuthButton(
                onClick = onLogin,
                isEnabled = state.isEnabled(),
                text = stringResource(id = R.string.log_in),
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            if (state.errorTypeValue != ErrorMessageType.NO_ERROR.value) Error(
                errorMessage = when (state.errorTypeValue) {
                    ErrorMessageType.WRONG_PASSWORD.value -> stringResource(R.string.wrong_password_message)
                    ErrorMessageType.NOT_EXIST.value -> stringResource(R.string.user_not_exist_message)
                    ErrorMessageType.NOT_VALIDATED.value -> stringResource(R.string.not_validated_message)
                    else -> stringResource(R.string.unknown_error_message)
                },
                modifier = Modifier.fillMaxWidth()
            )

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
            navigateToHome(context)
        }
    }
}

private fun navigateToHome(context: Context) {
    try {
        val intent = Intent(
            context,
            Class.forName("com.devfalah.ui.main.MainActivity")
        )
        ContextCompat.startActivity(context, intent, null)
        (context as? Activity)?.finish()
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
}