package com.devfalah.ui.screen.savedPosts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.savedPosts.SavedPostUIState
import com.devfalah.viewmodels.savedPosts.SavedPostsViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SavedPostsScreen(
    navController: NavController,
    viewModel: SavedPostsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    SavedPostsContent(
        state = state,
        navController = navController,
        onClickLike = viewModel::onClickLike,
        onClickComment = {
            navController.navigateToPostDetails(
                id = it.postId,
                isSaved = true,
                publisherId = it.publisherId,
                publisherName = it.publisherName,
                publisherUrl = it.publisherImage
            )
        },
        onClickRemoveSavedPost = viewModel::onClickRemoveSavedPost,
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) }
    )
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
        )
    }
}

@Composable
fun SavedPostsContent(
    state: SavedPostUIState,
    navController: NavController,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickRemoveSavedPost: (PostUIState) -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        AppBar(
            title = stringResource(id = R.string.saved_threads),
            navHostController = navController
        )

        LazyColumn(
            modifier = Modifier
                .background(LightBackgroundColor)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.posts) {
                PostItem(
                    state = it,
                    isContentExpandable = true,
                    isMyPost = it.publisherId == state.userId,
                    isClubPost = it.isFromClub,
                    showGroupName = true,
                    onClickLike = { onClickLike(it) },
                    onClickComment = { onClickComment(it) },
                    onClickSave = { onClickRemoveSavedPost(it) },
                    onClickProfile = onClickProfile,
                    onOpenLinkClick = onOpenLinkClick,
                    onClickPostSetting = { },
                )
            }
        }
    }
}