package com.devfalah.ui.screen.postCreation

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.clubCreation.showToastMessage
import com.devfalah.ui.screen.clubsDetail.navigateToClubDetails
import com.devfalah.ui.screen.home.navigateHome
import com.devfalah.ui.util.createFileFromContentUri
import com.devfalah.viewmodels.postCreation.PostCreationUIState
import com.devfalah.viewmodels.postCreation.PostCreationViewModel
import com.devfalah.viewmodels.postCreation.isEnabled
import com.devfalah.viewmodels.util.Constants.HOME_CLUB_ID
import com.devfalah.viewmodels.util.Constants.MAX_IMAGE_POST_SIZE
import com.devfalah.viewmodels.util.Constants.PROFILE_CLUB_ID
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PostCreationScreen(
    navController: NavController,
    viewModel: PostCreationViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                viewModel.onClickSelectImage(
                    createFileFromContentUri(
                        it,
                        context,
                        MAX_IMAGE_POST_SIZE
                    )
                )
            }
        }
    )
    PostCreationContent(
        state = state,
        navController = navController,
        onPrivacyChange = viewModel::onPrivacyChange,
        onPostChange = viewModel::onPostChange,
        onClickPost = viewModel::onClickPost,
        onRemoveImage = viewModel::onRemoveImage,
        onSelectImage = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }

    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
            darkIcons = false
        )
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostCreationContent(
    state: PostCreationUIState,
    navController: NavController,
    onPrivacyChange: (Int) -> Unit,
    onPostChange: (String) -> Unit,
    onClickPost: () -> Unit,
    onSelectImage: () -> Unit,
    onRemoveImage: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.create_thread_title),
                navHostController = navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            if (!state.isClub) {
                SegmentControlsWithIcon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.End),
                    items = listOf(
                        stringResource(R.string.public_privacy),
                        stringResource(R.string.private_privacy)
                    ),
                    icons = listOf(
                        painterResource(id = R.drawable.ic_menu_language),
                        painterResource(id = R.drawable.ic_clubs_filled),
                    ),
                    onItemSelection = onPrivacyChange,
                )
            }

            PostContent(
                value = state.postContent,
                modifier = Modifier.weight(1f),
                hint = stringResource(id = R.string.what_are_you_thinking_about),
                image = state.imageBitmap,
                onValueChange = onPostChange,
                onRemoveImage = onRemoveImage
            )
            PostFooter(
                onSelectImage = onSelectImage,
                onClickPost = onClickPost,
                isLoading = state.isLoading,
                isEnabled = state.isEnabled()
            )
        }

        val context = LocalContext.current
        LaunchedEffect(key1 = state.isSuccess, key2 = state.error.isNotEmpty()) {
            if (state.isSuccess) {
                goBack(state, navController)
            } else if (state.error.isNotBlank()) {
                showToastMessage(context, state.error)
            }
        }
    }
}

private fun goBack(state: PostCreationUIState, navController: NavController) {
    when (state.clubId) {
        HOME_CLUB_ID -> {
            navController.navigateHome()
        }
        PROFILE_CLUB_ID -> {
//            navController.navigateToProfile(state.id)
        }
        else -> {
            navController.navigateToClubDetails(groupId = state.clubId)
        }
    }
}

