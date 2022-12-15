package com.devfalah.ui.screen.profile

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.SetStatusBarColor
import com.devfalah.ui.modifiers.RemoveRippleEffect
import com.devfalah.ui.screen.createPost.navigateToCreatePost
import com.devfalah.ui.screen.friends.navigateToFriends
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.profile.composable.*
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.ProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { viewModel.onClickChangeImage(createFileFromContentUri(it, context)) }
        }
    )

    SetStatusBarColor(LightPrimaryBrandColor, darkIcons = false)

    ProfileContent(
        state,
        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.loading),
        onClickLike = viewModel::onClickLike,
        // should navigate to post screen details.
        onClickComment = { navController.navigate(Screen.CreatePost.screen_route) },
        onClickSave = viewModel::onClickSave,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickPostSetting = viewModel::onClickPostSetting,
        onClickSendMessage = {
            Toast.makeText(context, "not done yet.. ", Toast.LENGTH_LONG).show()
        },
        onChangeProfileImage = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
        onRefresh = viewModel::swipeToRefresh,
        onCreatePost = { navController.navigateToCreatePost(state.userDetails.userID) },
        onClickProfile = { if (!state.isMyProfile) { navController.navigateToProfile(it) } },
        onRetry = viewModel::getData,
        onClickFriends = { navController.navigateToFriends(it) },
        onOpenLinkClick = { openBrowser(context, it) }
    )

    LaunchedEffect(key1 = true) {
        if (state.minorError.isNotEmpty()) {
            Toast.makeText(context, state.minorError, Toast.LENGTH_LONG).show()
        }
    }

}

@Composable
fun ProfileContent(
    state: UserUIState,
    swipeRefreshState: SwipeRefreshState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickAddFriend: () -> Unit,
    onClickSendMessage: () -> Unit,
    onChangeProfileImage: () -> Unit,
    onRefresh: (Int) -> Unit,
    onClickPostSetting: (PostUIState) -> Unit,
    onCreatePost: () -> Unit,
    onClickProfile: (Int) -> Unit,
    onRetry: () -> Unit,
    onClickFriends: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit
) {
    if (state.majorError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize())
        Button(
            onClick = onRetry
        ) {
            Text(text = "Retry")
        }
    } else {
        val scrollState = rememberLazyListState()
        ManualPager(
            swipeRefreshState = swipeRefreshState,
            onRefresh = onRefresh,
            items = state.posts.map { it.postId },
            scrollState = scrollState,
            isRefreshing = state.loading,
            error = state.minorError,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item(key = state.userDetails.userID) {
                ProfileDetailsSection(
                    state.userDetails,
                    onChangeProfileImage = onChangeProfileImage,
                    onSendRequestClick = onClickAddFriend
                )
            }
            item {
                FriendsSection(
                    state.friends,
                    totalFriends = state.totalFriends,
                    modifier = Modifier
                        .RemoveRippleEffect { onClickFriends(state.userDetails.userID) }
                        .padding(horizontal = 16.dp)
                )
            }
            if (state.isMyProfile || state.userDetails.areFriends) {
                item {
                    PostCreatingSection(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onCreatePost = if (state.isMyProfile) {
                            onCreatePost
                        } else {
                            onClickSendMessage
                        },
                        isMyProfile = state.isMyProfile
                    )
                }
            }
            items(state.posts) {
                PostItem(
                    state = it,
                    isMyPost = true,
                    isContentExpandable = true,
                    onClickLike = { onClickLike(it) },
                    onClickComment = { onClickComment(it) },
                    onClickSave = { onClickSave(it) },
                    onClickPostSetting = { onClickPostSetting(it) },
                    onClickProfile = onClickProfile,
                    onOpenLinkClick = onOpenLinkClick,
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun createFileFromContentUri(fileUri: Uri, context: Context): File {
    var fileName = ""
    fileUri.let { returnUri ->
        context.contentResolver.query(returnUri, null, null, null)
    }?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }

    val iStream = context.contentResolver.openInputStream(fileUri)!!
    val outputDir = context.cacheDir!!

    val outputFile = File(outputDir, fileName)
    copyStreamToFile(iStream, outputFile)
    iStream.close()
    return outputFile
}

private fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
    inputStream.use { input ->
        val outputStream = FileOutputStream(outputFile)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)
            while (true) {
                val byteCount = input.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
        }
    }
}

