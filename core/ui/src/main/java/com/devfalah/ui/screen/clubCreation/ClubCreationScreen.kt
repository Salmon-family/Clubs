package com.devfalah.ui.screen.clubCreation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.viewmodels.clubCreation.ClubCreationUiState
import com.devfalah.viewmodels.clubCreation.ClubCreationViewModel

@Composable
fun ClubCreationScreen(
    navController: NavController,
    viewModel: ClubCreationViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    ClubCreationContent(
        navController = navController,
        state = state,
        onNameChange = viewModel::onNameTextChange,
        onDescriptionChange = viewModel::onDescriptionTextChange,
        onPrivacyChange = viewModel::onPrivacyChange,
        onClickCreateClub = viewModel::onClickCreateClub,
    )

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClubCreationContent(
    navController: NavController,
    state: ClubCreationUiState,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPrivacyChange: (Int) -> Unit,
    onClickCreateClub: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.create_club), navHostController = navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)

        ) {
            CustomTextField(
                title = stringResource(R.string.club_name),
                value = state.name,
                onValueChange = onNameChange,
                singleLine = true,
            )
            HeightSpacer16()
            CustomTextField(
                title = stringResource(R.string.description),
                hint = stringResource(R.string.description_hint),
                value = state.description,
                onValueChange = onDescriptionChange,
                maxChar = 500,
                showTextCount = true,
            )
            HeightSpacer16()
            Column {
                Text(text = stringResource(R.string.privacy))
                HeightSpacer8()
                SegmentControls(
                    items = listOf(
                        stringResource(R.string.public_privacy),
                        stringResource(R.string.private_privacy)
                    ),
                    onItemSelection = onPrivacyChange,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ButtonWithLoading(
                text = stringResource(id = R.string.create_club),
                onClick = onClickCreateClub,
                isLoading = state.isLoading,
                isEnabled = state.name.isNotEmpty(),
                modifier = Modifier.height(50.dp),
            )
        }
        val successMessage = stringResource(id = R.string.clubـcreated_successfully)
        LaunchedEffect(key1 = state.isSuccessful) {
            if (state.isSuccessful) {
                showToastMessage(context, successMessage)
            }
        }
    }

}


fun showToastMessage(
    context: Context,
    message: String,
) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

