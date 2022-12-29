package com.devfalah.ui.screen.userInformation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.viewmodels.userInformation.UserInformationUIState
import com.devfalah.viewmodels.userInformation.UserInformationViewModel
import com.devfalah.viewmodels.userInformation.isUpdateInformationButtonEnabled
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun UserInformationScreen(
    navController: NavController,
    viewModel: UserInformationViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    UserInformationContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onNameChange = viewModel::onNameTextChange,
        onTitleChange = viewModel::onTitleChange,
        onPasswordChange = viewModel::onPasswordTextChange,
        onClickSave = viewModel::onClickSave,
        navigateToProfile = { navController.navigateToProfile(state.id) }
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
fun UserInformationContent(
    state: UserInformationUIState,
    onClickBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickSave: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.edit_info),
                onBackButton = onClickBack,
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)

        ) {
            CustomTextField(
                title = stringResource(R.string.name),
                value = state.name,
                onValueChange = onNameChange,
                singleLine = true,
            )
            HeightSpacer16()
            CustomTextField(
                title = stringResource(R.string.title),
                hint = stringResource(R.string.title),
                value = state.title,
                onValueChange = onTitleChange,
            )
            HeightSpacer16()

            PasswordInputText(
                title = stringResource(R.string.password),
                password = state.password,
                onTextChange = onPasswordChange,
                isErrorTextShown = state.error.isNotEmpty()
            )
            HeightSpacer24()

            ButtonWithLoading(
                text = stringResource(id = R.string.save_button),
                onClick = onClickSave,
                isLoading = state.isLoading,
                isEnabled = state.isUpdateInformationButtonEnabled(),
                modifier = Modifier.fillMaxWidth(),
            )
        }

        LaunchedEffect(key1 = state.isSuccessful) {
            if (state.isSuccessful) {
                navigateToProfile()
            }
        }
    }

}