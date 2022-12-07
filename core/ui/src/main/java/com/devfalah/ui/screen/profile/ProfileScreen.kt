package com.devfalah.ui.screen.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.screen.profile.composable.*
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.ProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState
import java.io.ByteArrayOutputStream
import java.io.File

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
//    val file = File(context.assets.open(R.drawable.test_image))
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->



            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri!!))
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
            val image = stream.toByteArray()

            viewModel.onClickChangeImage(image,File(uri?.path))
        }
    )

    ProfileContent(
        state,
        onClickLike = viewModel::onClickLike,
        onClickComment = viewModel::onClickComment,
        onClickSave = viewModel::onClickSave,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickSendMessage = {
            Toast.makeText(context, "not done yet.. ", Toast.LENGTH_LONG).show()
        },
        onChangeProfileImage = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    )
}

@Composable
fun ProfileContent(
    state: UserUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickAddFriend: () -> Unit,
    onClickSendMessage: () -> Unit,
    onChangeProfileImage: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileDetailsSection(
                state.userDetails,
                modifier = Modifier.padding(horizontal = 16.dp),
                onChangeProfileImage = onChangeProfileImage
            )
        }
        if (!state.isMyProfile) {
            item {
                FriendOptionsSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    areFriends = state.userDetails.areFriends,
                    onClickAddFriend = onClickAddFriend,
                    onClickSendMessage = onClickSendMessage
                )
            }
        }
        item { AlbumSection(state.albums, modifier = Modifier.padding(horizontal = 16.dp)) }
        item { FriendsSection(state.friends, modifier = Modifier.padding(horizontal = 16.dp)) }
        item {
            PostCreatingSection(
                state.userDetails,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(state.posts) {
            ProfilePostItem(
                it,
                onClickLike = { onClickLike(it) },
                onClickComment = { onClickComment(it) },
                onClickSave = { onClickSave(it) }
            )
        }
    }
}
