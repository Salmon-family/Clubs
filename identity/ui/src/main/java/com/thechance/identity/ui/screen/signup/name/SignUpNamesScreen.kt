package com.thechance.identity.ui.screen.signup.name

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
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.signup.birthdate.navigateToBirthdateAndGander
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState

@Composable
fun SignUpFullNameScreen(
    navController: NavController,
    viewModel: SignupViewModel,
) {
    val state by viewModel.uiState.collectAsState()
    SignUpFullNameContent(
        state,
        onChangeFullName = viewModel::onChangeFullName,
        onChangeUserName = viewModel::onChangeUserName,
        onChangeJobTitle = viewModel::onChangeJobTitle,
        onValidate = viewModel::onValidateName,
        onClickBack = { navController.navigateUp() },
        onClickUserNameScreen = { navController.navigateToBirthdateAndGander() },
        onNavigate = {navController.navigateToLogInUserName()}
    )
}

@Composable
private fun SignUpFullNameContent(
    state: UserUIState,
    onClickBack: () -> Unit,
    onClickUserNameScreen: () -> Unit,
    onChangeFullName: (String) -> Unit,
    onChangeUserName: (String) -> Unit,
    onChangeJobTitle: (String) -> Unit,
    onValidate: () -> Boolean,
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        BackButton(onClick = onClickBack)

        Text(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 8.dp),
        )

        EmailDescriptionText(
            email = state.email,
        )

        Text(
            text = stringResource(id = R.string.full_naame),
            style = Typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 14.dp)
        )

        InputText(
            type = KeyboardType.Text,
            placeHolder = stringResource(id = R.string.name_hint),
            text = state.firstName,
            onTextChange = onChangeFullName,
        )

        Text(
            text = stringResource(id = R.string.user_name),
            style = Typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 14.dp)
        )

        InputText(
            type = KeyboardType.Text,
            placeHolder = stringResource(id = R.string.user_name_hint),
            text = state.username,
            onTextChange = onChangeUserName
        )

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
            onTextChange = onChangeJobTitle
        )

        AuthButton(
            onClick = onClickUserNameScreen,
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