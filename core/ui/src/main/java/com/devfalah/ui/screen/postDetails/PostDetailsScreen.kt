package com.devfalah.ui.screen.postDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.screen.postDetails.composable.CommentItem
import com.devfalah.ui.screen.postDetails.composable.CustomTextFiled
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState
import com.devfalah.viewmodels.postDetails.PostDetailsViewModel
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostDetailsScreen(
//    navController: NavController,
    viewModel: PostDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    PostDetailsContent(
        state,
        commentText = state.comment,
        onValueChanged = viewModel::onChanceMessage,
        sendMessage = viewModel::sendMessage,
        onClickLike = viewModel::onClickLike,
        onClickLikeComment = viewModel::onClickLikeComment,
        onClickComment = viewModel::onClickComment,
        onClickSave = viewModel::onClickSave,
//        onClickDeletedComment = viewModel::onClickDeletedComment,
        )
}

@Composable
fun PostDetailsContent(
    state: PostDetailsUIState,
    commentText: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickLikeComment: (CommentUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
//    onClickDeletedComment: (CommentUIState) -> Unit,
) {
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
                .fillMaxWidth()
                .padding(bottom = 56.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    isMyProfile = state.isMyProfile,
                    onClickLike = { onClickLike(it) },
                    onClickComment = { onClickComment(it) },
                    onClickSave = { onClickSave(it) },
                    onClickPostSetting = { },
                    isContentExpandable = true
                )
            }
            items(state.comments) {
                CommentItem(state = it,
                    onClickLike = onClickLikeComment,
//                    onClickDeletedComment = onClickDeletedComment
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
        )
    }
}
