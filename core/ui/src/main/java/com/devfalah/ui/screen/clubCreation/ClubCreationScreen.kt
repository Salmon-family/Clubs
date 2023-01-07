package com.devfalah.ui.screen.clubCreation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.clubCreation.ClubCreationUiState
import com.devfalah.viewmodels.clubCreation.ClubCreationViewModel
import com.devfalah.viewmodels.clubCreation.isCreateClubButtonEnabled
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ClubCreationScreen(
    navController: NavController,
    viewModel: ClubCreationViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    ClubCreationContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onNameChange = viewModel::onNameTextChange,
        onDescriptionChange = viewModel::onDescriptionTextChange,
        onPrivacyChange = viewModel::onPrivacyChange,
        onClickCreateClub = viewModel::onClickCreateClub,
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }

}

@Composable
fun ClubCreationContent(
    state: ClubCreationUiState,
    onClickBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPrivacyChange: (Int) -> Unit,
    onClickCreateClub: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.create_club),
                onBackButton = onClickBack,
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
                .padding(horizontal = 24.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            CustomTextField(
                title = stringResource(R.string.club_name),
                value = state.name,
                onValueChange = onNameChange,
                singleLine = true,
                maxChar = 30,
                hint = stringResource(id = R.string.enter_club_name)
            )

            CustomTextField(
                title = stringResource(R.string.description),
                hint = stringResource(R.string.description_hint),
                value = state.description,
                onValueChange = onDescriptionChange,
                maxChar = 500,
                showTextCount = true,
                shape = RoundedCornerShape(16.dp),
                maxLines = 5
            )

            Column {
                Text(
                    text = stringResource(R.string.privacy),
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                HeightSpacer8()
                SegmentControls(
                    items = listOf(
                        stringResource(R.string.public_privacy),
                        stringResource(R.string.private_privacy)
                    ),
                    onItemSelection = onPrivacyChange,
                )
            }

            ButtonWithLoading(
                text = stringResource(id = R.string.create_club),
                onClick = onClickCreateClub,
                isLoading = state.isLoading,
                isEnabled = state.isCreateClubButtonEnabled(),
                modifier = Modifier.fillMaxWidth(),
            )
        }

        val successMessage = stringResource(id = R.string.clubـcreated_successfully)
        LaunchedEffect(key1 = state.isSuccessful) {
            if (state.isSuccessful) {
                showToastMessage(context, successMessage)
                onClickBack()
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

