package com.devfalah.ui.screen.postDetails

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.screen.postDetails.composable.CommentItem
import com.devfalah.ui.screen.postDetails.composable.CustomTextFiled
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.Typography
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState
import com.devfalah.viewmodels.postDetails.PostDetailsViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostDetailsScreen(
//    navController: NavController,
    viewModel: PostDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { viewModel.setComment(state.comment/*, createFileFromContentUri(it, context)*/) }
        }
    )

    PostDetailsContent(
        state,
        commentText = state.comment,
        onValueChanged = viewModel::onChanceComment,
        sendMessage = viewModel::sendComment,
        addImageWithComment = {
            singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        onClickLike = viewModel::onClickLike,
        onClickLikeComment = viewModel::onClickLikeComment,
        onClickComment = viewModel::onClickComment,
        onClickSave = viewModel::onClickSave,
        onClickDeletedComment = viewModel::onClickDeletedComment,
        onClickEditComment = viewModel::onClickEditComment,
        onValueChangedEdited = viewModel::onChanceCommentEditing,
        sendMessageEdited = viewModel::sendCommentEdited,
        closeDialog = viewModel::closeDialog,
    )
}

@Composable
fun PostDetailsContent(
    state: PostDetailsUIState,
    commentText: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    addImageWithComment: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickLikeComment: (CommentUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit,
    onValueChangedEdited: (String) -> Unit,
    sendMessageEdited: (CommentUIState) -> Unit,
    closeDialog: () -> Unit,
) {
    if (state.loading) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = LightPrimaryBrandColor,
            )
        }
    } else {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBackgroundColor),
        ) {
            val (post, comments, textField) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .constrainAs(comments) {
                        top.linkTo(post.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 64.dp, top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    PostItem(
                        modifier = Modifier
                            .constrainAs(post) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(),
                        state = state.postDetails,
                        onClickLike = { onClickLike(it) },
                        onClickComment = { onClickComment(it) },
                        onClickSave = { onClickSave(it) },
                        onClickPostSetting = { },
                        isContentExpandable = true,
                        maxLineContentExpand = 5,
                        onClickProfile = { },
                        onOpenLinkClick = { },
                    )
                }
                item {
                    Text(text = "Comments",
                        style = Typography.body2,
                        modifier = Modifier.padding(start = 16.dp))
                }
                items(state.comments) {
                    CommentItem(
                        state = it,
                        post = state,
                        onClickLikeComment = onClickLikeComment,
                        onClickDeletedComment = onClickDeletedComment,
                        onClickEditComment = onClickEditComment,
                        onValueChanged = onValueChangedEdited,
                        sendMessage = sendMessageEdited,
                        closeDialog = closeDialog,
                    )
                }
            }
            CustomTextFiled(
                modifier = Modifier
                    .constrainAs(textField) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                text = commentText,
                onValueChanged = onValueChanged,
                sendMessage = sendMessage,
                addImageWithComment = addImageWithComment,
            )
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

