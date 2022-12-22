package com.devfalah.ui.screen.userInformation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.viewmodels.userInformation.UserInformationUIState
import com.devfalah.viewmodels.userInformation.UserInformationViewModel
import com.devfalah.viewmodels.userInformation.isUpdateInformationButtonEnabled


@Composable
fun UserInformationScreen(
    navController: NavController,
    viewModel: UserInformationViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    UserInformationContent(
        navController = navController,
        state = state,
        onNameChange = viewModel::onNameTextChange,
        onTitleChange = viewModel::onTitleChange,
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
    onPasswordChange: (String) -> Unit,
    onClickSave: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.edit_info), navHostController = navController)
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
                onTextChange = onPasswordChange
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
                navController.navigateToProfile(state.id)
            }
        }
    }

}


