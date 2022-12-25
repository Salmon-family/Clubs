package com.devfalah.ui.screen.postDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
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
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.clubCreation.showToastMessage
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.postDetails.compose.CommentItem
import com.devfalah.ui.screen.postDetails.compose.CommentOnThread
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
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
        navController = navController,
        state = state,
        onClickLike = viewModel::onClickLikePost,
        onClickSave = viewModel::onClickSavePost,
        onRefresh = viewModel::getPostComments,
        onClickSendComment = viewModel::onClickSendComment,
        onCommentValueChanged = viewModel::onCommentValueChanged,
        onDeletePost = viewModel::onDeletePost,
        onClickDeleteComment = viewModel::onClickDeleteComment,
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) },
        onClickCommentLike = viewModel::onClickLikeComment
    )

    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController, color = LightBackgroundColor, darkIcons = true
        )
    }
}

@Composable
fun PostDetailsContent(
    navController: NavController,
    state: PostDetailsUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onRefresh: (Int) -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit,
    onClickSendComment: () -> Unit,
    onCommentValueChanged: (String) -> Unit,
    onClickCommentLike: (CommentUIState) -> Unit,
    onDeletePost: (PostUIState) -> Unit,
    onClickDeleteComment: (CommentUIState) -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        AppBar(
            title = stringResource(id = R.string.post_details),
            navHostController = navController
        )

        ManualPager(
            modifier = Modifier.weight(1f),
            onRefresh = onRefresh,
            contentPadding = PaddingValues(top = 16.dp),
            isLoading = state.isLoading,
            error = state.error,
            isEndOfPager = state.isEndOfPager,
        ) {
            item("PostDetails") {
                PostItem(
                    state = state.post,
                    isContentExpandable = true,
                    isClubPost = state.isFromClub,
                    isMyPost = state.post.publisherId == state.id,
                   //////////
                    showGroupName= false,

                    onClickLike = onClickLike,
                    onClickComment = { },
                    onClickSave = onClickSave,
                    onClickProfile = onClickProfile,
                    onClickPostSetting = onDeletePost,
                    onOpenLinkClick = onOpenLinkClick
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
                        color = LightSecondaryBlackColor,
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

    val context = LocalContext.current
    LaunchedEffect(key1 = state.isPostDeleted) {
        if (state.isPostDeleted) {
            navController.navigateUp()
        }
    }

    LaunchedEffect(key1 = state.minorError.isNotEmpty()) {
        if (state.minorError.isNotEmpty()) {
            showToastMessage(context, state.minorError)
        }
    }

}
