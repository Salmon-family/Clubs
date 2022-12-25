package com.thechance.identity.ui.screen.signup.jobtitle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.thechance.identity.ui.screen.signup.birthdate.navigateToBirthdateAndGander
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState

@Composable
fun JobTitleScreen(
    navController: NavController,
    viewModel: SignupViewModel
){
    val state by viewModel.uiState.collectAsState()
    JobTitleContent(
        state = state,
        onClickBack = { navController.navigateUp() },
        onClickContinue = { navController.navigateToBirthdateAndGander() },
        onChangeJobTitle = viewModel::onChangeJobTitle,
        onValidate = viewModel::onValidateJobTitle,
        onNavigate = {navController.navigateToLogInUserName()}
    )
}

@Composable
private fun JobTitleContent(
    state: UserUIState,
    onClickBack: () -> Unit,
    onClickContinue: () -> Unit,
    onChangeJobTitle: (String) -> Unit,
    onValidate: () -> Boolean,
    onNavigate: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackButton(onClickBack)

        Text(
            text = stringResource(id = R.string.job_title),
            style = Typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 14.dp)
        )

        InputText(
            type = KeyboardType.Text,
            placeHolder = stringResource(id = R.string.job_hint),
            text = state.jobTitle,
            onTextChange = onChangeJobTitle,
        )

        AuthButton(
            onClick = onClickContinue,
            buttonModifier = Modifier
                .padding(horizontal = 8.dp, vertical = 24.dp)
                .fillMaxWidth(),
            isEnabled = onValidate.invoke(),
            text = stringResource(id = R.string.continue_label),
        )

        NavigateToAnotherScreen(
            hintText = R.string.navigate_to_login,
            navigateText = R.string.log_in,
            onNavigate = onNavigate
        )
    }
}