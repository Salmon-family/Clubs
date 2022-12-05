package com.thechance.identity.ui.screen.login.username

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
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.AuthText
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.composable.InputText
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.spacer.SpacerVertical12
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.ui.screen.login.password.navigateToLogInPassword
import com.thechance.identity.viewmodel.login.LoginUIState
import com.thechance.identity.viewmodel.login.LoginViewModel

@Composable
fun LogInUserNameScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
){
    val uiState by viewModel.uiState.collectAsState()
    LogInUserNameContent(
        uiState = uiState,
        onChangeUserName = viewModel::onChangeUserName,
        onClickContinue = { navController.navigateToLogInPassword() },
        onClickBack = {navController.navigateUp()}
    )
}

@Composable
private fun LogInUserNameContent(
    uiState: LoginUIState,
    onChangeUserName: (String) -> Unit,
    onClickContinue: () -> Unit,
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
            text = stringResource(id = R.string.user_name_question),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.user_name),
            style = Typography.subtitle2,
            color = LightSecondaryBlackColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputText(
            type = KeyboardType.Text,
            placeHolder = stringResource(id = R.string.user_name_place_holder),
            text = uiState.userName,
            onTextChange = onChangeUserName
        )

        SpacerVertical24()
        AuthButton(
            onClick = onClickContinue,
            text = stringResource(id = R.string.continue_label),
            buttonModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}

