package com.devfalah.ui.screen.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.devfalah.ui.composable.*
import com.devfalah.ui.image.navigateToImageScreen
import com.devfalah.ui.screen.friends.navigateToFriends
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.composable.FriendsSection
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.screen.userInformation.navigateToEditUserInformation
import com.devfalah.ui.util.createFileFromContentUri
import com.devfalah.ui.util.observeAsState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.ProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState
import com.devfalah.viewmodels.util.ChatNavigation.FRIEND_ID
import com.devfalah.viewmodels.util.ChatNavigation.PACKAGE_CHAT_NAME
import com.devfalah.viewmodels.util.Constants.MAX_IMAGE_PROFILE_SIZE
import com.devfalah.viewmodels.util.Constants.PROFILE_CLUB_ID
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(
    navController: NavController, viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectedImageUri = uri
                uri?.let {
                    viewModel.onClickChangeImage(
                        createFileFromContentUri(it, context, MAX_IMAGE_PROFILE_SIZE)
                    )
                }
            })
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()
    LaunchedEffect(key1 = lifecycleState.value) {
        if (lifecycleState.value == Lifecycle.Event.ON_RESUME) {
            viewModel.refreshProfileThreads()
        }
    }

    ProfileContent(
        state,
        selectedImageUri = selectedImageUri,
        onClickLike = viewModel::onClickLike,
        onClickComment = {
            navController.navigateToPostDetails(id = it.postId)
        },
        onClickSave = viewModel::onClickSave,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickPostSetting = viewModel::onClickPostSetting,
        onClickSendMessage = {
            navigateToConversation(
                context = context, state.userDetails.userID
            )
        },
        onChangeProfileImage = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
        onRefresh = viewModel::getProfileThreads,
        onCreatePost = { navController.navigateToPostCreation(PROFILE_CLUB_ID) },
        onClickProfile = {
            if (!state.userDetails.isMyProfile) {
                navController.navigateToProfile(it)
            }
        },
        onRetry = viewModel::retryGetProfileData,
        onClickFriend = { navController.navigateToProfile(it) },
        onClickMoreFriends = { navController.navigateToFriends(state.userDetails.userID) },
        onOpenLinkClick = { openBrowser(context, it) },
        onEditUserInformation = {
            if (state.userDetails.isMyProfile) {
                navController.navigateToEditUserInformation()
            }
        },
        onImageClick = { navigateToImageScreen(context, it) },
        onClickBackButton = { navController.popBackStack() },
        onRemoveFriend = viewModel::onRemoveFriend
    )

    LaunchedEffect(key1 = state.minorError) {
        if (state.minorError.isNotEmpty()) {
            Toast.makeText(context, state.minorError, Toast.LENGTH_LONG).show()
        }
    }

    val color = MaterialTheme.colors.primary
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }

}

@Composable
fun ProfileContent(
    state: UserUIState,
    selectedImageUri: Uri?,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickAddFriend: () -> Unit,
    onClickSendMessage: () -> Unit,
    onChangeProfileImage: () -> Unit,
    onRefresh: () -> Unit,
    onClickPostSetting: (PostUIState) -> Unit,
    onCreatePost: () -> Unit,
    onClickProfile: (Int) -> Unit,
    onRetry: () -> Unit,
    onClickFriend: (Int) -> Unit,
    onClickMoreFriends: () -> Unit,
    onOpenLinkClick: (String) -> Unit,
    onEditUserInformation: () -> Unit,
    onImageClick: (String) -> Unit,
    onClickBackButton: () -> Unit,
    onRemoveFriend: () -> Unit,
) {

    val lazyScrollState = rememberLazyListState()

    Box {
        if (state.majorError.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.loading) {
            Loading()
        } else {

            Scaffold(
                topBar = {
                    MotionAppBar(
                        state = state,
                        lazyScrollState = lazyScrollState,
                        selectedImageUri = selectedImageUri,
                        onClickAddFriend = onClickAddFriend,
                        onChangeProfileImage = onChangeProfileImage,
                        onEditUserInformation = onEditUserInformation,
                        onClickBackButton = onClickBackButton,
                        onRemoveFriend = onRemoveFriend
                    )
                }
            ) { paddingValues ->

                ManualPager(
                    modifier = Modifier.padding(paddingValues),
                    onRefresh = onRefresh,
                    scrollState = lazyScrollState,
                    contentPadding = PaddingValues(bottom = 16.dp),
                    isLoading = state.isPagerLoading,
                    error = state.minorError,
                    isEndOfPager = state.isEndOfPager
                ) {
                    item {
                        Spacer(
                            Modifier
                                .height(30.dp)
                                .background(MaterialTheme.colors.background)
                                .fillMaxWidth()
                        )
                    }
                    item(key = state.friends) {
                        FriendsSection(
                            state.friends,
                            totalFriends = state.totalFriends,
                            onClickMoreFriends = onClickMoreFriends,
                            onClickFriend = onClickFriend,
                        )
                    }
                    if (state.userDetails.isMyProfile || state.userDetails.areFriends) {
                        item("profileDetails") {
                            PostCreatingSection(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                onCreatePost = if (state.userDetails.isMyProfile) {
                                    onCreatePost
                                } else {
                                    onClickSendMessage
                                },
                                isMyProfile = state.userDetails.isMyProfile
                            )
                        }
                    }
                    items(state.posts) {
                        PostItem(
                            state = it,
                            isContentExpandable = true,
                            isClubPost = false,
                            showGroupName = false,
                            onClickLike = onClickLike,
                            onClickComment = onClickComment,
                            onClickSave = onClickSave,
                            onClickPostSetting = onClickPostSetting,
                            onClickProfile = onClickProfile,
                            onOpenLinkClick = onOpenLinkClick,
                            onImageClick = onImageClick
                        )
                    }
                }
            }
        }
    }
}

private fun navigateToConversation(context: Context, friendId: Int) {
    try {
        val intent = Intent(context, Class.forName(PACKAGE_CHAT_NAME))
        intent.putExtra(FRIEND_ID, friendId)
        startActivity(context, intent, Bundle())
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
}
