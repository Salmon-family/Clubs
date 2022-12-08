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
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.screen.profile.composable.*
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.ProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState
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
            uri?.let {

                viewModel.onClickChangeImage(createFileFromContentUri(it, context))
            }
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

        // for test 

        item(state.bitmap != null) {
            Image(
                bitmap = state.bitmap!!.asImageBitmap(),
                contentDescription = "some useful description",
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun createFileFromContentUri(fileUri: Uri, context: Context): File {

    var fileName: String = ""

    fileUri.let { returnUri ->
        context.contentResolver.query(returnUri, null, null, null)
    }?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }

//  For extract file mimeType
    val fileType: String? = fileUri.let { returnUri ->
        context.contentResolver.getType(returnUri)
    }

    val iStream: InputStream =
        context.contentResolver.openInputStream(fileUri)!!
    val outputDir: File = context?.cacheDir!!
    val outputFile: File = File(outputDir, fileName)
    copyStreamToFile(iStream, outputFile)
    iStream.close()
    return outputFile
}

fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
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