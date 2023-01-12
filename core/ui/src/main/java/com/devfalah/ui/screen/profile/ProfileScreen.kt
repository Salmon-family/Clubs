package com.devfalah.ui.screen.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.image.navigateToImageScreen
import com.devfalah.ui.modifiers.flipWithLanguage
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.friends.navigateToFriends
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.composable.FriendsSection
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.screen.userInformation.navigateToEditUserInformation
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightPrimaryBrandTransparentColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.ui.util.createFileFromContentUri
import com.devfalah.ui.util.observeAsState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.ProfileViewModel
import com.devfalah.viewmodels.userProfile.UserDetailsUIState
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

@OptIn(ExperimentalMotionApi::class)
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

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.option_scene).readBytes().decodeToString()
    }

    Box() {
        if (state.majorError.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.loading) {
            Loading()
        } else {

            MotionLayout(
                motionScene = MotionScene(
                    content = motionScene
                ), modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .background(LightPrimaryBrandColor)
                        .layoutId("firstBox")
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = selectedImageUri ?: state.userDetails.profilePicture,
                            error = painterResource(id = R.drawable.test_image)
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(motionProperties("imageBox").value.color("background"))
                        .layoutId("imageBox")
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("appBar"),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .nonRippleEffect { onClickBackButton() }
                            .flipWithLanguage(),
                        tint = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
                    if (state.userDetails.isMyProfile) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.setting),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onEditUserInformation()
                                },
                            tint = Color.White
                        )
                    }
                }

                Text(
                    text = state.userDetails.title,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 56.dp)
                        .layoutId("textTitle"),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    fontFamily = PlusJakartaSans,
                    color = WhiteColor,
                    maxLines = 1
                )
                Text(
                    text = state.userDetails.name,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .layoutId("textName"),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight(motionInt("textName", "fontWeight")),
                    fontSize = motionFontSize("textName", "fontSize"),
                    fontFamily = PlusJakartaSans,
                    color = WhiteColor,
                    maxLines = 2
                )

                Box(
                    modifier = Modifier.layoutId("boxProfile"),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .height(120.dp)
                            .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                            .background(MaterialTheme.colors.background),
                    )
                }

                Image(
                    painter = rememberAsyncImagePainter(
                        model = (selectedImageUri ?: state.userDetails.profilePicture).toString()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .layoutId("imageProfile")
                        .border(4.dp, MaterialTheme.colors.background, CircleShape),
                    contentScale = ContentScale.Crop
                )

                val onClick = if (state.userDetails.isMyProfile) {
                    onChangeProfileImage
                } else if (state.userDetails.areFriends) {
                    onRemoveFriend
                } else if (!state.userDetails.isRequestSend) {
                    onClickAddFriend
                } else {
                    {}
                }
                getPainterProfileIcon(state.userDetails)?.let {
                    Box(modifier = Modifier
                        .nonRippleEffect {
                            onClick()
                        }
                        .clip(CircleShape)
                        .layoutId("imagePicker")
                        .background(MaterialTheme.colors.surface)
                        .border(4.dp, MaterialTheme.colors.background, CircleShape),
                        contentAlignment = Alignment.Center) {
                        getPainterProfileIcon(state.userDetails)?.let { painter ->
                            Icon(
                                tint = LightPrimaryBrandColor,
                                painter = painter,
                                contentDescription = null
                            )
                        }
                    }
                }

                ManualPager(
                    modifier = Modifier.layoutId("scrollItems"),
                    onRefresh = onRefresh,
                    contentPadding = PaddingValues(bottom = 16.dp),
                    isLoading = state.isPagerLoading,
                    error = state.minorError,
                    isEndOfPager = state.isEndOfPager
                ) {
                    item(key = state.friends) {
                        FriendsSection(
                            state.friends,
                            totalFriends = state.totalFriends,
                            modifier = Modifier.layoutId("scrollItems"),
                            onClickMoreFriends = onClickMoreFriends,
                            onClickFriend = onClickFriend,
                        )
                    }
                    if (state.userDetails.isMyProfile || state.userDetails.areFriends) {
                        item("profileDetails") {
                            PostCreatingSection(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .layoutId("scrollItems"),
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
                            modifier = Modifier.layoutId("scrollItems"),
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

@Composable
private fun getPainterProfileIcon(
    userDetails: UserDetailsUIState
): Painter? {
    return if (userDetails.isMyProfile) {
        painterResource(id = R.drawable.ic_camera)
    } else if (userDetails.isRequestSend) {
        painterResource(id = R.drawable.ic_requsted_add_user)
    } else if (!userDetails.areFriends) {
        painterResource(id = R.drawable.ic_add_user)
    } else if (userDetails.areFriends) {
        painterResource(id = R.drawable.delete_user)
    } else {
        null
    }
}
