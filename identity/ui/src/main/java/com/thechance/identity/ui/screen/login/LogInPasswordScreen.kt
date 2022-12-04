package com.thechance.identity.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.spacer.SpacerVertical12
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.ui.extension.navigateToHome
import com.thechance.identity.viewmodel.login.LoginUIState
import com.thechance.identity.viewmodel.login.LoginViewModel

@Composable
fun LogInPasswordScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    LogInPasswordContent(
        uiState = uiState,
        onChangePassword = viewModel::onChangePassword,
        onLogin = {
            viewModel.onLogin()
            navController.navigateToHome()
        },
        onClickBack = { navController.navigateUp() }
    )
}

@Composable
fun LogInPasswordContent(
    uiState: LoginUIState,
    onChangePassword: (String) -> Unit,
    onLogin: () -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SpacerVertical12()
        BackButton(onClick = onClickBack)

        SpacerVertical(52.dp)
        AuthText(
            text = stringResource(id = R.string.log_in),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        SpacerVertical8()
        EmailDescriptionText(
            text1 = stringResource(id = R.string.using),
            color1 = LightSecondaryBlackColor,
            text2 = stringResource(id = R.string.email_place_holder),
            color2 = LightPrimaryBrandColor,
            text3 = stringResource(id = R.string.to_login)
        )

        SpacerVertical(height = 32.dp)
        AuthText(
            text = stringResource(id = R.string.your_password),
            style = Typography.subtitle2,
            color = LightSecondaryBlackColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputText(
            type = KeyboardType.Password,
            image = R.drawable.ic_hide,
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = uiState.password,
            onTextChange = onChangePassword
        ) {

        }

        SpacerVertical24()
        AuthButton(
            onClick = onLogin,
            text = stringResource(id = R.string.log_in),
            buttonModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}