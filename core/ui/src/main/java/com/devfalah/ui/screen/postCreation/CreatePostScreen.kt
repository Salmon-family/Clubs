package com.devfalah.ui.screen.postCreation

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.devfalah.ui.screen.profile.createFileFromContentUri
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.createPost.CreatePostViewModel
import com.devfalah.viewmodels.createPost.PostCreationUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostCreationScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { viewModel.onClickSelectImage(createFileFromContentUri(it, context)) }
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

    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
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

            PostContent(
                value = state.postContent,
                modifier = Modifier.weight(1f),

                hint = stringResource(id = R.string.what_are_you_thinking_about),
                image = state.imageBitmap,
                onValueChange = onPostChange,
                onRemoveImage = onRemoveImage
            )

            PostFooter(onSelectImage = onSelectImage, onClickPost = onClickPost)

        }

        val context = LocalContext.current
        LaunchedEffect(key1 = state.isSuccess, key2 = state.error.isNotEmpty()) {
            if (state.isSuccess) {
                navController.navigateUp()
            } else if (state.error.isNotBlank()) {
                showToastMessage(context, state.error)
            }
        }
    }
}


