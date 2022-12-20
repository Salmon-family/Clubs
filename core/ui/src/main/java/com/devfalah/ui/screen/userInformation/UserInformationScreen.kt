package com.devfalah.ui.screen.userInformation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.viewmodels.editUserInformation.EditUserViewModel
import com.devfalah.viewmodels.editUserInformation.UserInformationUIState
import com.devfalah.viewmodels.editUserInformation.isUpdateInformationButtonEnabled


@Composable
fun UserInformationScreen(
    navController: NavController,
    viewModel: EditUserViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    UserInformationContent(
        navController = navController,
        state = state,
        onNameChange = viewModel::onNameTextChange,
        onTitleChange = viewModel::onTitleChange,
        onBioChange = viewModel::onBioTextChange,
        onPasswordChange = viewModel::onPasswordTextChange,
        onClickSave = viewModel::onClickSave,
    )

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserInformationContent(
    navController: NavController,
    state: UserInformationUIState,
    onNameChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickSave: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.create_club), navHostController = navController)
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
            CustomTextField(
                title = stringResource(R.string.bio),
                hint = stringResource(R.string.bio),
                value = state.bio,
                onValueChange = onBioChange,
                maxChar = 500,
                singleLine = false,
                showTextCount = true,
            )
            HeightSpacer16()
            CustomTextField(
                title = stringResource(R.string.password),
                value = state.password,
                onValueChange = onPasswordChange,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            HeightSpacer24()

            ButtonWithLoading(
                text = stringResource(id = R.string.save_button),
                onClick = onClickSave,
                isLoading = state.isLoading,
                isEnabled = state.isUpdateInformationButtonEnabled(),
                modifier = Modifier.height(50.dp),
            )
        }

        LaunchedEffect(key1 = state.isSuccessful) {
            if (state.isSuccessful) {
                navController.navigateUp()
            }
        }
    }

}

