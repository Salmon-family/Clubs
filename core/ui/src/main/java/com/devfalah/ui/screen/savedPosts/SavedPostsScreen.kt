package com.devfalah.ui.screen.savedPosts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.viewmodels.savedPosts.SavedPostUIState
import com.devfalah.viewmodels.savedPosts.SavedPostsViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SavedPostsScreen(
    navController: NavController,
    viewModel: SavedPostsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    SavedPostsContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onClickLike = viewModel::onClickLike,
        onClickComment = {
            navController.navigateToPostDetails(id = it.postId, publisherId = it.publisherId)
        },
        onClickRemoveSavedPost = viewModel::onClickRemoveSavedPost,
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) }
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
fun SavedPostsContent(
    state: SavedPostUIState,
    onClickBack: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickRemoveSavedPost: (PostUIState) -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {

        AppBar(
            title = stringResource(id = R.string.saved_threads),
            onBackButton = onClickBack
        )

        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.posts) {
                PostItem(
                    state = it,
                    isContentExpandable = true,
                    isClubPost = it.isFromClub,
                    showGroupName = true,
                    onClickLike = onClickLike,
                    onClickComment = onClickComment,
                    onClickSave = onClickRemoveSavedPost,
                    onClickProfile = onClickProfile,
                    onOpenLinkClick = onOpenLinkClick,
                    onClickPostSetting = { },
                )
            }
        }
    }
}