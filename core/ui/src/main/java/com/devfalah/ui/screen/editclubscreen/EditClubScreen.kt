package com.devfalah.ui.screen.editclubscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.clubCreation.showToastMessage
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.editclub.EditClubUiState
import com.devfalah.viewmodels.editclub.EditClubViewModel
import com.devfalah.viewmodels.editclub.isCreateClubButtonEnabled
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun EditClubScreen(
    navController: NavController,
    editClubViewModel: EditClubViewModel = hiltViewModel(),
) {
    val state by editClubViewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    EditClubContent(
        uiState = state,
        onClickBack = { navController.popBackStack() },
        onNameChanged = editClubViewModel::onChangedClubName,
        onDescriptionChanged = editClubViewModel::onDescriptionChanged,
        onPrivacyChanged = editClubViewModel::onPrivacyChanged,
        onClickEditClub = editClubViewModel::onClickEditClub, onClickCancel = {
            navController.popBackStack()
        })

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
private fun EditClubContent(
    uiState: EditClubUiState,
    onClickBack: () -> Unit,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onPrivacyChanged: (Int) -> Unit,
    onClickEditClub: () -> Unit,
    onClickCancel: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.edit_club),
                onBackButton = onClickBack,
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                title = stringResource(R.string.club_name),
                value = uiState.clubName,
                onValueChange = onNameChanged,
                singleLine = true,
            )

            Column {
                Text(
                    text = stringResource(R.string.privacy),
                    color = MaterialTheme.colors.primaryVariant
                )
                HeightSpacer8()
                SegmentControls(
                    items = listOf(
                        stringResource(R.string.public_privacy),
                        stringResource(R.string.private_privacy)
                    ),
                    onItemSelection = onPrivacyChanged,
                )
            }

            CustomTextField(
                title = stringResource(R.string.description),
                hint = stringResource(R.string.description_hint),
                value = uiState.clubDescription,
                onValueChange = onDescriptionChanged,
                maxChar = 500,
                showTextCount = true,
                maxLines = 5,
                shape = RoundedCornerShape(16.dp)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    Button(
                        onClick = onClickCancel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondary,
                            contentColor = LightPrimaryBrandColor,
                        ),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    ButtonWithLoading(
                        text = stringResource(id = R.string.edit_club),
                        onClick = onClickEditClub,
                        isLoading = uiState.isLoading,
                        isEnabled = uiState.isCreateClubButtonEnabled(),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
            val successMessage = stringResource(id = R.string.club_edited_successfully)
            LaunchedEffect(key1 = uiState.isSuccessful) {
                if (uiState.isSuccessful) {
                    showToastMessage(context, successMessage)
                }
            }
        }
    }
}