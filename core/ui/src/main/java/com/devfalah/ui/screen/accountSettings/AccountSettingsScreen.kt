package com.devfalah.ui.screen.accountSettings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.viewmodels.accountSettings.AccountSettingsUiState
import com.devfalah.viewmodels.accountSettings.AccountSettingsViewModel
import com.devfalah.viewmodels.accountSettings.isEditButtonEnabled
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AccountSettingsScreen(
    navController: NavController,
    viewModel: AccountSettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    AccountSettingsContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onNewPasswordChange = viewModel::onNewPasswordChange,
        onCurrentPasswordChange = viewModel::onCurrentPasswordChange,
        onClickEdit = viewModel::onClickEdit
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountSettingsContent(
    state: AccountSettingsUiState,
    onClickBack: () -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onCurrentPasswordChange: (String) -> Unit,
    onClickEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.change_password),
                onBackButton = onClickBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(vertical = 16.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            PasswordInputText(
                title = stringResource(R.string.new_password),
                password = state.newPassword,
                onTextChange = onNewPasswordChange
            )

            PasswordInputText(
                title = stringResource(R.string.password),
                password = state.currentPassword,
                onTextChange = onCurrentPasswordChange,
                isErrorTextShown = state.error.isNotEmpty()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CancelButton(
                    modifier = Modifier.weight(1f),
                    onClick = onClickBack
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
            if (state.isSuccessful) onClickBack()
        }
    }


}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenu() {
    AccountSettingsContent(
        state = AccountSettingsUiState(),
        onNewPasswordChange = {},
        onCurrentPasswordChange = {},
        onClickEdit = {},
        onClickBack = {}
    )
}