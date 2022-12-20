package com.devfalah.ui.screen.postDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.screen.postDetails.composable.CommentItem
import com.devfalah.ui.screen.postDetails.composable.CustomTextFiled
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState
import com.devfalah.viewmodels.postDetails.PostDetailsViewModel
import com.devfalah.viewmodels.postDetails.isSendCommentButtonEnabled
import com.devfalah.viewmodels.userProfile.PostUIState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostDetailsScreen(
    navController: NavController,
    viewModel: PostDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    PostDetailsContent(
        state = state,
        onRefresh = viewModel::getAllComments,
        commentText = state.comment,
        onValueChanged = viewModel::onChanceComment,
        sendMessage = viewModel::sendComment,
        onClickLike = viewModel::onClickLike,
        onClickLikeComment = viewModel::onClickLikeComment,
        onClickComment = viewModel::onClickComment,
        onClickSave = viewModel::onClickSave,
        onClickDeletedComment = viewModel::onClickDeletedComment,
        onClickEditComment = viewModel::onClickEditComment,
        onValueChangedEdited = viewModel::onChanceCommentEditing,
        sendMessageEdited = viewModel::sendCommentEdited,
        closeDialog = viewModel::closeDialog,
        onRetry = viewModel::getData,
        )
}

@Composable
fun PostDetailsContent(
    state: PostDetailsUIState,
    onRefresh: (Int) -> Unit,
    commentText: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickLikeComment: (CommentUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit,
    onValueChangedEdited: (String) -> Unit,
    sendMessageEdited: (CommentUIState) -> Unit,
    closeDialog: () -> Unit,
    onRetry: () -> Unit,
    ) {
    if (state.error.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = onRetry ) {
                    Text(text = "Refresh Screen")
                }
                HeightSpacer8()
                CircularProgressIndicator()
            }
        }
    } else {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBackgroundColor),
        ) {
            val (post, comments, textField) = createRefs()
            ManualPager(
                modifier = Modifier
                    .constrainAs(comments) {
                        top.linkTo(post.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onRefresh = onRefresh,
                contentPadding = PaddingValues(bottom = 64.dp, top = 8.dp),
                isLoading = state.isPagerLoading,
                error = state.pagerError,
                isEndOfPager = state.isEndOfPager,
            ) {
                item(key = state.postDetails.postId) {
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
                        isMyPost = false,
                    )
                }
                item {
                    Text(text = "Comments",
                        style = AppTypography.body2,
                        modifier = Modifier.padding(start = 16.dp))
                }
                items(state.comments) {
                    CommentItem(
                        state = it,
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
                isEnabled = state.isSendCommentButtonEnabled(),
            )
        }
    }
}