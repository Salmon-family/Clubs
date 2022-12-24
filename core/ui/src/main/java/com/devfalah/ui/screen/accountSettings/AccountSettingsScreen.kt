package com.devfalah.ui.screen.accountSettings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.accountSettings.AccountSettingsUiState
import com.devfalah.viewmodels.accountSettings.AccountSettingsViewModel
import com.devfalah.viewmodels.accountSettings.isEditButtonEnabled

@Composable
fun AccountSettingsScreen(
    navController: NavController,
    viewModel: AccountSettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    AccountSettingsContent(
        navController = navController,
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onNewPasswordChange = viewModel::onNewPasswordChange,
        onCurrentPasswordChange = viewModel::onCurrentPasswordChange,
        onClickEdit = viewModel::onClickEdit
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountSettingsContent(
    navController: NavController,
    state: AccountSettingsUiState,
    onEmailChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onCurrentPasswordChange: (String) -> Unit,
    onClickEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.account_settings),
                navHostController = navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBackgroundColor)
                .padding(vertical = 16.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CustomTextField(
                title = stringResource(R.string.email),
                value = state.email,
                onValueChange = onEmailChange,
                singleLine = true,
            )

            PasswordInputText(
                title = stringResource(R.string.new_password),
                password = state.newPassword,
                onTextChange = onNewPasswordChange
            )

            PasswordInputText(
                title = stringResource(R.string.password),
                password = state.currentPassword,
                onTextChange = onCurrentPasswordChange
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CancelButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navController.popBackStack() }
                )

                ButtonWithLoading(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.edit),
                    onClick = onClickEdit,
                    isLoading = state.isLoading,
                    isEnabled = state.isEditButtonEnabled()
                )
            }

        }

        LaunchedEffect(key1 = state.isSuccessful) {
            if (state.isSuccessful) navController.popBackStack()
        }
    }


}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenu() {
    AccountSettingsContent(
        navController = NavController(LocalContext.current),
        state = AccountSettingsUiState(),
        onEmailChange = {},
        onNewPasswordChange = {},
        onCurrentPasswordChange = {},
        onClickEdit = {}
    )
}