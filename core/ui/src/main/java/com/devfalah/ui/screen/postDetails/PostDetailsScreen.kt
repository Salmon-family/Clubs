package com.devfalah.ui.screen.postDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.image.navigateToImageScreen
import com.devfalah.ui.screen.clubCreation.showToastMessage
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.postDetails.compose.CommentItem
import com.devfalah.ui.screen.postDetails.compose.CommentOnThread
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState
import com.devfalah.viewmodels.postDetails.PostDetailsViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PostDetailsScreen(
    navController: NavController,
    viewModel: PostDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    PostDetailsContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onClickLike = viewModel::onClickLikePost,
        onClickSave = viewModel::onClickSavePost,
        onRefresh = viewModel::getPostComments,
        onClickSendComment = viewModel::onClickSendComment,
        onCommentValueChanged = viewModel::onCommentValueChanged,
        onDeletePost = viewModel::onDeletePost,
        onClickDeleteComment = viewModel::onClickDeleteComment,
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) },
        onClickCommentLike = viewModel::onClickLikeComment,
        onClickPostDelete = { navController.navigateUp() },
        onRetry = viewModel::getData,
        onImageClick = { navigateToImageScreen(context, it) }
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@Composable
fun PostDetailsContent(
    state: PostDetailsUIState,
    onClickBack: () -> Unit,
    onClickPostDelete: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onRefresh: () -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit,
    onClickSendComment: () -> Unit,
    onCommentValueChanged: (String) -> Unit,
    onClickCommentLike: (CommentUIState) -> Unit,
    onDeletePost: (PostUIState) -> Unit,
    onClickDeleteComment: (CommentUIState) -> Unit,
    onRetry: () -> Unit,
    onImageClick: (String) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {

        AppBar(
            title = stringResource(id = R.string.post_details),
            onBackButton = onClickBack
        )
        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            Loading()
        } else if (!state.post.isFound) {
            ErrorEmpty(text = stringResource(id = R.string.deleted_post))
        } else {
            ManualPager(
                modifier = Modifier.weight(1f),
                onRefresh = onRefresh,
                contentPadding = PaddingValues(top = 16.dp),
                isLoading = state.isPagerLoading,
                error = state.minorError,
                isEndOfPager = state.isEndOfPager,
            ) {
                item("PostDetails") {
                    PostItem(
                        state = state.post,
                        isContentExpandable = true,
                        isClubPost = state.post.groupName.isNotEmpty(),
                        showGroupName = state.post.groupName.isNotEmpty(),
                        onClickLike = onClickLike,
                        onClickComment = { },
                        onClickSave = onClickSave,
                        onClickProfile = onClickProfile,
                        onClickPostSetting = onDeletePost,
                        onOpenLinkClick = onOpenLinkClick,
                        onImageClick = onImageClick
                    )
                }

                item("commentTitle") {
                    if (state.comments.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            text = stringResource(id = R.string.replies),
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.secondaryVariant,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = PlusJakartaSans
                        )
                    }
                }

                items(state.comments) {
                    CommentItem(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        onClickLike = { onClickCommentLike(it) },
                        onClickDeleteComment = onClickDeleteComment
                    )
                }
            }
            CommentOnThread(
                isEnabled = state.commentText.isNotEmpty(),
                text = state.commentText,
                onClickSendComment = onClickSendComment,
                onValueChanged = onCommentValueChanged
            )
        }
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = state.isPostDeleted) {
        if (state.isPostDeleted) {
            onClickPostDelete()
        }
    }

    LaunchedEffect(key1 = state.minorError.isNotEmpty()) {
        if (state.minorError.isNotEmpty()) {
            showToastMessage(context, state.minorError)
        }
    }

}
